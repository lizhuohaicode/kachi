package com.team.aop;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.team.annotation.PermissionLog;
import com.team.exception.KachiException;
import com.team.util.IPUtils;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team.dao.auth.OperationLogDao;
import com.team.model.auth.OperationLog;
import com.team.model.auth.TbAuthUser;
import com.team.util.CommonUtil;
import com.team.util.IConstant;
import com.team.util.LogManager;

/**
 * 业务拦截权限和记录日志
 * 创建日期：2018-1-23上午12:56:13
 * author:wuzhiheng
 */
@Aspect
@Component
public class PermissionLogAop {
	
	@Autowired
	private OperationLogDao operationLogDao;

	@Pointcut(value="@annotation(com.team.annotation.PermissionLog)")
	public void cutService(){
		
	}
	
	@Around("cutService()")
	public Object excuteLog(ProceedingJoinPoint point) throws Throwable{

		MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        PermissionLog logInfo = method.getAnnotation(PermissionLog.class);
        HttpServletRequest request = CommonUtil.getRequest();
		Map<String, Object> permission = CommonUtil.getUserPermission();

		String uri = request.getRequestURI().replace(request.getContextPath(), "");
		//1.执行方法之前，判断权限--onlyLog为true则代表这个只执行记录日志，不过滤权限，默认是false
        if(!logInfo.onlyLog()){
    		boolean ok = false;
    		for (Entry<String, Object> entry : permission.entrySet()) {
    			if(uri.matches(entry.getKey())){
					ok=true;
    				break;
				}
    		}
    		if(!ok){
    			throw new KachiException(IConstant.NO_PERMISSION);
    		}
        }

		TbAuthUser user = null;
		Object result = null;
        if("用户登出".equals(logInfo.value())){
			user = CommonUtil.getUser();
			result = point.proceed();
		}else {
			//2.执行目标方法
			result = point.proceed();
			user = CommonUtil.getUser();
		}

		//3.记录日志，如果用户为空则不记录
		if(user!=null){
			PermissionLog bussiness = point.getTarget().getClass().getAnnotation(PermissionLog.class);
			String bussinesstype = bussiness.value();
			if(bussiness==null || "".equals(bussinesstype)){
				throw new RuntimeException("记录日志需要在这个类上标志这个类的业务");
			}

			String operation = "";
			//注解在方法上，如果value没有值，那么在对应的权限里面找，注意，这种情况主要请求的路径和方法名一致
			if("".equals(logInfo.value())){
				for (Entry<String, Object> entry : permission.entrySet()) {
					if(uri.matches(entry.getKey())){
						operation = entry.getValue().toString();
						break;
					}
				}
				
				if((operation.indexOf("添加") > -1 && !CommonUtil.StringIsNull(request.getParameter("id")))){
					operation = operation.replace("添加", "修改");
				}else if(operation.indexOf("修改") > -1 && CommonUtil.StringIsNull(request.getParameter("id"))){
					operation = operation.replace("修改", "添加");
				}
				
			}else{
				operation = logInfo.value();
			}
			
			//下面是正式开启一个异步的任务来执行这个记录日志
			final OperationLog operationLog = new OperationLog(user.getName(), bussinesstype, operation,
					CommonUtil.getParamDesc(request),user.getDepartmentId(), IPUtils.getIpAddr(request),CommonUtil.browserInfo(request));
			LogManager.me().executeLog(new TimerTask() {
				@Override
				public void run() {
					operationLogDao.saveLog(operationLog);
				}
			});
		}

		return result;
	}


	public static void main(String[] args){
		System.out.println("/simcard/getByPool".matches("simcard/getByPool"));
	}
}
