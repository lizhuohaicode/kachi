package com.team.service.auth.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.dao.auth.TbAuthPermissionDao;
import com.team.dao.auth.TbAuthRoleDao;
import com.team.model.auth.TbAuthPermission;
import com.team.model.auth.TbAuthRole;
import com.team.model.auth.TbAuthUser;
import com.team.service.auth.TbAuthPermissionService;
import com.team.service.impl.BaseService;
import com.team.util.CommonUtil;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2018-1-18下午10:19:11
 * author:wuzhiheng
 */
@Service
@Transactional
public class TbAuthPermissionServiceImpl extends BaseService implements TbAuthPermissionService{

	@Autowired
	private TbAuthPermissionDao tbAuthPermissionDao;
	@Autowired
	private TbAuthRoleDao tbAuthRoleDao;
	
	public List<TbAuthPermission> getMenuByUser(TbAuthUser user){
		List<TbAuthPermission> list = null;
		if(CommonUtil.listNotBlank(user.getRoles())){
			list = tbAuthPermissionDao.getMenuByRole(user.getRoles());
		}
		
		return  CommonUtil.bulidTree(list,false);
	}

	@Override
	public ReturnMsg getFunByUser(TbAuthUser user,Integer id) {
		ReturnMsg returnMsg = super.successTip();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", id);
		map.put("list", user.getRoles());
		returnMsg.setData(tbAuthPermissionDao.getFunByRole(map));
		return returnMsg;
	}

	@Override
	public List<TbAuthPermission> getPermissionTree() {
		Integer userId = CommonUtil.getUser().getId();
		List<TbAuthPermission> list = tbAuthPermissionDao.getAllPermissionByCurUser(userId);
		return CommonUtil.bulidTree(list,true);
	}

	@Override
	public List<TbAuthPermission> getPermissionByUser(Integer id) {
		return tbAuthPermissionDao.getPermissionByUser(id);
	}

	@Override
	public ReturnMsg grantPermission(Integer userId, String ids) {
		ReturnMsg returnMsg = null;
		if(userId != null){
			Map<String, Object> map = new HashMap<String, Object>();
			returnMsg = super.successTip();
			
			//1.找出用户的专属角色，没有则添加一个
			List<TbAuthRole> roles = tbAuthRoleDao.getUserRole(userId);
			TbAuthRole role = CommonUtil.listNotBlank(roles)?roles.get(0):null;
			if(role==null){
				role = new TbAuthRole("普通用户", "USER_"+userId);
				tbAuthRoleDao.insertRole(role);
				map.put("userId", userId);
				map.put("roleId", role.getId());
				tbAuthRoleDao.inserUserRole(map);
				map.clear();
			}
			//2.插入角色-权限关系前，先把原本的关联关系清了
			tbAuthPermissionDao.updateRolePermission(userId);
			//3插入角色-权限的关联关系
			if(!CommonUtil.StringIsNull(ids)){
				map.put("roleId", role.getId());
				map.put("array", ids.split(","));
				tbAuthPermissionDao.insertRolePermission(map);
				//特殊处理，针对这个用户也是系统角色的情况
				if(isAdmin(roles)!=null){
					map.put("roleId", isAdmin(roles).getId());
					tbAuthPermissionDao.insertRolePermission(map);
				}
			}
		}else{
			returnMsg = super.errorTip();
		}
		
		return returnMsg;
	}

	//判断当前角色列表是否包含系统管理员的角色
	private TbAuthRole isAdmin(List<TbAuthRole> roles){
		if(CommonUtil.listNotBlank(roles)){
			for (TbAuthRole role : roles) {
				if("GROUP_ADMIN".equals(role.getCode())){
					return role;
				}
			}
		}
		return null;
	}

	@Override
	public ReturnMsg updateStatus(Integer id) {
		int count = tbAuthPermissionDao.updateStatus(id);
		return count>0?super.successTip():super.errorTip();
	}

	@Override
	public ReturnMsg saveOrUpdatePermission(TbAuthPermission permission) {
		if(permission.getId()!=null){
			tbAuthPermissionDao.updatePermission(permission);
		}else{
			tbAuthPermissionDao.insertPermission(permission);
			tbAuthPermissionDao.insertAdminPermission(permission.getId());
		}
		return super.successTip();
	}

	
}
