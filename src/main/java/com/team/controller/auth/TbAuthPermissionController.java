package com.team.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.model.auth.TbAuthPermission;
import com.team.service.auth.TbAuthPermissionService;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2018-1-19上午12:55:55
 * author:wuzhiheng
 */
@RestController
public class TbAuthPermissionController {

	@Autowired
	private TbAuthPermissionService tbAuthPermissionService;
	
	@PostMapping("/getPermissionByUser")
	public Object getPermissionByUser(Integer id){
		return tbAuthPermissionService.getPermissionByUser(id);
	}
	
	@PostMapping("/grantPermission")
	public ReturnMsg grantPermission(Integer userId,String ids){
		return tbAuthPermissionService.grantPermission(userId, ids);
	}
	
	@PostMapping("/deletePermission")
	public ReturnMsg deletePermission(Integer id){
		return tbAuthPermissionService.updateStatus(id);
	}
	
	@PostMapping("/savePermission")
	public ReturnMsg savePermission(TbAuthPermission permission,String icon){
		permission.setIconCls(icon);
		return tbAuthPermissionService.saveOrUpdatePermission(permission);
	}
	
}
