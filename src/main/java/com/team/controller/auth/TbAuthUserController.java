package com.team.controller.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.aop.PermissionLog;
import com.team.model.auth.TbAuthUser;
import com.team.service.auth.TbAuthUserService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2018-1-18下午10:27:37
 * author:wuzhiheng
 */
@RestController
@PermissionLog("用户管理")
public class TbAuthUserController {

	@Autowired
	private TbAuthUserService tbAuthUserService;
	
	@PostMapping("/getUserList")
	public ResultList getUserList(Integer status, String name,
			Integer departmentId,int page,int rows,HttpServletRequest  request){
		Integer dId = CommonUtil.getUser(request).getDepartmentId(); 
		return tbAuthUserService.getUserList(status, name,departmentId,dId, page, rows);
	}
	
	@PostMapping("/saveUser")
	@PermissionLog(key="name_用户名;departmentId_部门编号;id_用户id")
	public ReturnMsg saveUser(TbAuthUser user){
		return tbAuthUserService.saveOrUpdateUser(user);
	}
	
	@PostMapping("/updateUserStatus")
	@PermissionLog(key="name_用户名;status_状态;id_用户id")
	public ReturnMsg updateUserStatus(TbAuthUser user){
		return tbAuthUserService.updateUserStatus(user);
	}
	
	@PostMapping("/deleteUser")
	@PermissionLog(key="name_用户名")
	public ReturnMsg deleteUser(Integer id){
		return tbAuthUserService.deleteUser(id);
	}
	
	@GetMapping("/getUserCount")
	public int getUserCount(String name){
		return tbAuthUserService.getUserCount(name);
	}
	
	@PostMapping("/modifyPwd")
	public ReturnMsg modifyPwd(HttpServletRequest request,String oldPwd,String newPwd){
		TbAuthUser user = CommonUtil.getUser(request);
		return tbAuthUserService.modifyPwd(user, oldPwd, newPwd);
	}
	
	@PostMapping("/resetPwd")
	@PermissionLog(key="name_用户名;id_用户id")
	public ReturnMsg resetPwd(Integer id){
		return tbAuthUserService.resetPwd(id);
	}
	
}
