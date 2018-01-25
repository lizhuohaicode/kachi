package com.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.SimCardDao;
import com.team.dao.SimPoolDao;
import com.team.model.SimPool;
import com.team.service.SimPoolService;
import com.team.util.CommonUtil;
import com.team.util.IConstant;
import com.team.vo.OutlineInfo;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-18下午3:40:55 author:wuzhiheng
 */
@Transactional
@Service
public class SimPoolServiceImpl implements SimPoolService {

	@Autowired
	private SimPoolDao simPoolDao;
	@Autowired
	private SimCardDao simCardDao;

	@Override
	/**
	 * 根据代理商id查找其所有的卡池
	 */
	public ResultList getSimPoolList(Integer dId, Integer spid, String name,
			Integer isActive, int page, int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dId", CommonUtil.changeDepartmentId(dId));
		map.put("spid", spid);
		map.put("name", name);
		map.put("isActive", isActive);
		List<SimPool> list = simPoolDao.getSimPoolList(map);
		PageInfo<SimPool> pageInfo = new PageInfo<SimPool>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

	@Override
	/**
	 * 根据代理商查找卡池的总览信息
	 *@param departmentId
	 *@return
	 *return
	 */
	public ReturnMsg getOutlineInfo(Integer dId) {
		ReturnMsg returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		List<OutlineInfo> list = simPoolDao.getOutlineInfo(CommonUtil
				.changeDepartmentId(dId));
		OutlineInfo info = null;
		if (CommonUtil.listNotBlank(list)) {
			info = list.get(0);
			info.setOfflinePoolCount(info.getSimPoolCount()
					- info.getOnlinePoolCount());
			returnMsg.setData(info);
		}
		return returnMsg;
	}

	@Override
	/**
	 * 根据卡池id更新卡池的代理商，顺带更新卡池下流量卡的代理商
	 */
	public ReturnMsg modifyDept(SimPool simPool) {
		if (simPoolDao.updateDept(simPool) > 0) {
			simCardDao.updateCardDept(simPool);
			return IConstant.MSG_OPERATE_SUCCESS;
		}
		return IConstant.MSG_OPERATE_ERROR;
	}

	@Override
	/**
	 * 保存新增卡池
	 */
	public ReturnMsg saveSimPool(SimPool simPool) {
		int count = 0;
		count = simPoolDao.insertSimPool(simPool);
		if (count > 0) {
			return IConstant.MSG_OPERATE_SUCCESS;
		} else {
			return IConstant.MSG_OPERATE_ERROR;
		}
	}

}
