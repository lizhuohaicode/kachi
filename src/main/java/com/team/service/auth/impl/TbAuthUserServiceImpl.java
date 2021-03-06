package com.team.service.auth.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.auth.TbAuthRoleDao;
import com.team.dao.auth.TbAuthUserDao;
import com.team.model.auth.TbAuthRole;
import com.team.model.auth.TbAuthUser;
import com.team.service.auth.TbAuthUserService;
import com.team.service.impl.BaseService;
import com.team.util.CommonUtil;
import com.team.util.IConstant;
import com.team.util.MD5Utils;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2018-1-18下午10:19:11
 * author:wuzhiheng
 */
@Service
@Transactional
public class TbAuthUserServiceImpl extends BaseService implements TbAuthUserService{

	@Autowired
	private TbAuthUserDao tbAuthUserDao;
	@Autowired
	private TbAuthRoleDao tbAuthRoleDao;
	
	@Override
	/**
	 * 通过登录名查找用户信息
	 */
	public TbAuthUser getUserByName(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("status", 0);
		List<TbAuthUser> list = tbAuthUserDao.getUserList(map);
		return CommonUtil.listNotBlank(list)?list.get(0):null;
	}
	
	@Override
	/**
	 * 根据条件查找用户列表
	 */
	public ResultList getUserList(Integer status, String name,Integer departmentId,int page,int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("name", name);
		map.put("departmentId", departmentId);
		map.put("dId", CommonUtil.getDId());
		map.put("status", status);
		List<TbAuthUser> list = tbAuthUserDao.getUserList(map);
		PageInfo<TbAuthUser> pageInfo = new PageInfo<TbAuthUser>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

	@Override
	public ReturnMsg saveOrUpdateUser(TbAuthUser user) {
		if(user.getId()==null){
			//1.创建用户
			user.setPassWord(CommonUtil.encryptPassword(user.getPassWord()));
			tbAuthUserDao.saveUser(user);
			//2.创建用户角色
			TbAuthRole role = new TbAuthRole("普通用户", "USER_"+user.getId());
			tbAuthRoleDao.insertRole(role);

			//4.创建用户-角色的关联关系
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", user.getId());
			map.put("roleId", role.getId());
			tbAuthRoleDao.inserUserRole(map);
		}else{
			//1.更新用户表
			tbAuthUserDao.updateUser(user);
		}
		
		return super.successTip();
	}


	@Override
	public ReturnMsg updateUserStatus(TbAuthUser user) {
		int count= tbAuthUserDao.updateStatus(user);
		return count>0?super.successTip():super.errorTip();
	}

	@Override
	public int getUserCount(String name) {
		return tbAuthUserDao.getUserCount(name);
	}

	@Override
	public ReturnMsg deleteUser(Integer id) {
		int count= tbAuthUserDao.deleteUser(id);
		return count>0?super.successTip():super.errorTip();
	}

	@Override
	public ReturnMsg modifyPwd(TbAuthUser user,String oldPwd, String newPwd) {
		ReturnMsg returnMsg = null;
		if(CommonUtil.validateUser(user,oldPwd)){
			newPwd = CommonUtil.encryptPassword(newPwd);
			user.setPassWord(newPwd);
			tbAuthUserDao.modifyPwd(user);
			returnMsg = super.successTip();
		}else{
			returnMsg = new ReturnMsg(IConstant.CODE_ERROR,"旧密码不正确");
		}
		return returnMsg;
	}

	@Override
	public ReturnMsg resetPwd(Integer id) {
		TbAuthUser user = new TbAuthUser();
		user.setId(id);
		user.setPassWord(CommonUtil.encryptPassword("888888"));
		tbAuthUserDao.modifyPwd(user);
		return super.successTip();
	}

}
