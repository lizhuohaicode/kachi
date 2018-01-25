package com.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.team.model.SimPool;
import com.team.vo.OutlineInfo;

/**
 * 卡池表的相关操作	m_simpool
 * 创建日期：2017-12-18下午3:38:21
 * author:wuzhiheng
 */
public interface SimPoolDao {
	
	/**
	 * 根据代理商id查找其所有的卡池
	 *@param departmentId
	 *@return
	 *return
	 */
	public List<SimPool> getSimPoolList(Map<String, Object> map);
	
	/**
	 * 根据代理商查找卡池的总览信息
	 *@param departmentId
	 *@return
	 *return
	 */
	public List<OutlineInfo> getOutlineInfo(@Param("dId")Integer dId);
	
	/**
	 * 更新卡池的代理商
	 *@param simPool
	 *@return
	 *return
	 */
	public int updateDept(SimPool simPool);

  /**
   * 增加卡池
   * 
   * @param simPool
   * @return
   */
  public int insertSimPool(SimPool simPool);
	
}
