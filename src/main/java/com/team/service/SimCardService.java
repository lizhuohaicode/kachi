package com.team.service;

import com.team.model.SimCard;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;


/**
 * 卡表相关操作	m_simcard
 * 创建日期：2017-12-18下午3:40:42
 * author:wuzhiheng
 */
public interface SimCardService {
	
	public ReturnMsg getSimCardByPool(Integer cpId);
	
	public ReturnMsg deleteSimCard(String ids);
	
	public ResultList getSimCardList(Integer departmentId,Integer dId,Integer cpId,String number,
			Integer status,int page,int rows);
	
	public ReturnMsg getOutlineInfo(Integer dId);

	public ReturnMsg update(SimCard simCard);
	
}
