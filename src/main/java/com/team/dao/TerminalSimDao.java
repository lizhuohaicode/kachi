package com.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.team.model.TerminalSim;

/**
 * 终端对应的卡的相关操作	m_terminal_sim
 * 创建日期：2017-12-18下午3:38:21
 * author:wuzhiheng
 */
public interface TerminalSimDao {
	
	/**
	 * 通过终端tsid找到对应的SIM卡
	 *@param tsid
	 *@return
	 *return
	 */
	List<TerminalSim> getTerminalSimByTsid(@Param("tsid")Integer tsid);

	List<TerminalSim> getTerminalSimByImsi(@Param("imsi") Long imsi);
	
	
	/**
	 * 查询终端卡列表
	 *@param map
	 *@return
	 *return
	 */
	List<TerminalSim> getTerminalSimList(Map<String, Object> map);
	
	/**
	 * 支持批量删除在线终端
	 *@param list
	 *@return
	 *return
	 */
	int deleteTerminalByTsid(@Param("tsid") Integer tsid);

	/**
	 * 根据id列表查找
	 * @param ids
	 * @return
	 */
	TerminalSim getByTsid(@Param("tsid") Integer tsid);

	
}
