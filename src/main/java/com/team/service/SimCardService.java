package com.team.service;


import com.team.model.ResultList;

/**
 * 卡表相关操作	m_simcard
 * 创建日期：2017-12-18下午3:40:42
 * author:wuzhiheng
 */
public interface SimCardService {
	
	public ResultList getSimCardByPool(int cpId);
	
}