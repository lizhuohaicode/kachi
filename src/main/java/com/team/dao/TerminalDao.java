package com.team.dao;

import java.util.List;
import java.util.Map;

import com.team.model.Terminal;


/**
 * 终端的相关操作	m_terminal
 * 创建日期：2017-12-18下午3:38:21
 * author:wuzhiheng
 */
public interface TerminalDao {
	
	/**
	 * 通过代理商找出终端列表
	 *@param map
	 *@return
	 *return
	 */
	public List<Terminal> getTerminalByDeparment(Map<String, Object> map);
	
	/**
	 * 根据id批量删除终端
	 *@param list
	 *@return
	 *return
	 */
	public int deleteTerminalByIds(List<Integer> list);
	
	/**
	 * 插入一条终端数据
	 *@param terminal
	 *@return
	 *return
	 */
	public int insertTerminal(Terminal terminal);
	
	/**
	 * 根据id更新终端信息
	 *@param terminal
	 *@return
	 *return
	 */
	public int updateTerminalById(Terminal terminal);
	
}