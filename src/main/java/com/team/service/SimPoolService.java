package com.team.service;


import com.team.model.SimPool;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 卡池表相关操作	m_simpool
 * 创建日期：2017-12-18下午3:40:42
 * author:wuzhiheng
 */
public interface SimPoolService {
	
	public ResultList getSimPoolList(Integer dId,Integer spid,String name,Integer isActive,int page,int rows);
	
	public ReturnMsg getOutlineInfo(Integer dId);
	
	public ReturnMsg modifyDept(SimPool simPool);
	
	public ReturnMsg saveSimPool(SimPool simPool);
	
}
