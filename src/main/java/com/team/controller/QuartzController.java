package com.team.controller;

import com.team.annotation.PermissionLog;
import com.team.model.QuartzCron;
import com.team.service.QuartzService;
import com.team.service.impl.BaseService;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午10:30 2018/7/2
 */
@RestController
@RequestMapping("/quartz")
@PermissionLog("/定时任务")
public class QuartzController extends BaseService{

    @Autowired
    private QuartzService quartzService;

    @RequestMapping("/reset")
    @PermissionLog
    public ReturnMsg set(Integer minute,Integer status,Integer isHandle) throws Exception{

        if(minute == null || minute<1 || minute>59 || status == null || (status!=0 && status!=1)|| isHandle==null || (isHandle!=0 && isHandle!=1)){
            return errorTip("参数错误");
        }

        return  quartzService.scheduleUpdateCronTrigger(minute,status,isHandle);
    }

    @PostMapping("/getNow")
    public QuartzCron getNow(){
        return quartzService.getNow();
    }


}
