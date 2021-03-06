package com.team.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.annotation.PermissionLog;
import com.team.model.ReadPoolDept;
import com.team.model.SimPool;
import com.team.service.ReadPoolDeptService;
import com.team.service.SimPoolService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-18下午3:51:14
 * author:wuzhiheng
 */
@RestController
@PermissionLog("卡池管理")
@RequestMapping("/simpool")
public class SimPoolController {

	@Autowired
	private SimPoolService simPoolService;
	@Autowired
	private ReadPoolDeptService readPoolDeptService;
	
	@PostMapping("/list")
	public ResultList list(Integer spid,String name,Integer isActive,
			int page,int rows){
		return simPoolService.getSimPoolList(spid,name,isActive, page, rows);
	}
	
	@PostMapping("/outlineInfo")
	public ReturnMsg outlineInfo(){
		return simPoolService.getOutlineInfo();
	}
	
	@PostMapping("/give")
	@PermissionLog
	public ReturnMsg give(ReadPoolDept readPoolDept){
		return readPoolDeptService.saveReadPoolDept(readPoolDept);
	}
	
	@PostMapping("/update")
	@PermissionLog
	public ReturnMsg update(SimPool simPool){
		ReturnMsg returnMsg =  simPoolService.update(simPool);
		return returnMsg;
	}
	
	@PostMapping("/save")
	@PermissionLog
	public ReturnMsg save(SimPool simPool) {
		return simPoolService.saveSimPool(simPool);
	}

}
