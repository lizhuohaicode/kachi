package com.team.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.team.model.Terminal;
import com.team.service.CostDayService;
import com.team.service.TerminalService;
import com.team.service.TerminalSimService;
import com.team.util.IConstant;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-21下午4:56:08
 * author:wuzhiheng
 */
@RestController
public class TerminalController {

	@Autowired
	private TerminalService terminalService;
	@Autowired
	private CostDayService costDayService;
	@Autowired
	private TerminalSimService terminalSimService;
	
	@PostMapping("/getTerminalByDept")
	public ResultList getTerminalByDeparment(String status,String tsid,String activated,int page,int rows){
		return terminalService.getTerminalByDeparment(null, tsid, status,activated, page, rows);
	}
	
	@PostMapping("/deleteTerminalByIds")
	public ReturnMsg deleteTerminalByIds(String ids){
		return terminalService.deleteTerminalByIds(ids);
	}
	
	@PostMapping("/saveTerminal")
	public ReturnMsg saveTerminal(Terminal terminal){
		int count = 0;
		if(terminal.getId()!=null){
			count = terminalService.updateTerminalById(terminal);
		}else{
			count = terminalService.insertTerminal(terminal);
		}
		if(count > 0){
			return IConstant.MSG_OPERATE_SUCCESS;
		}
		return IConstant.MSG_OPERATE_ERROR;
	} 
	
	@PostMapping("/uploadTerminalByExcel")
	public ReturnMsg uploadTerminalByExcel(MultipartFile file){
		ReturnMsg returnMsg = terminalService.getTerminalList(file);
		if("200".equals(returnMsg.getCode())){
			List<Terminal> list = (List<Terminal>) returnMsg.getData();
			for (Terminal terminal : list) {
				terminalService.insertTerminal(terminal);
			}
			returnMsg.setData(list.size());
		}
		return returnMsg;
	} 
	
	@PostMapping("/getCostDayByTsid")
	public ResultList getCostDayByTsid(Integer tsid,int page,int rows){
		return costDayService.getCostDayByTsid(tsid, page, rows);
	}
	
	@PostMapping("/getTerminalSimByTsid")
	public ReturnMsg getTerminalSimByTsid(Integer tsid){
		return terminalSimService.getTerminalSimByTsid(tsid);
	} 
	
}