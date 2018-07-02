package com.team.service;

import javax.annotation.Resource;

import com.team.dao.QuartzCronDao;
import com.team.model.QuartzCron;
import com.team.service.impl.BaseService;
import com.team.vo.ReturnMsg;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QuartzService extends BaseService{

    @Resource(name = "jobDetail")
    private JobDetail jobDetail;

    @Resource(name = "jobTrigger")
    private CronTrigger cronTrigger;

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    @Autowired
    private QuartzCronDao quartzCronDao;

    public ReturnMsg scheduleUpdateCronTrigger(int minute) throws SchedulerException {

        String newCron = "0 */"+minute+" * * * ?";

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
        String currentCron = trigger.getCronExpression();// 当前Trigger使用的
        System.out.println(currentCron);
        System.out.println(newCron);
        if(!currentCron.equals(newCron)){
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(newCron);
            // 按新的cronExpression表达式重新构建trigger
            trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
            trigger = trigger.getTriggerBuilder().withIdentity(cronTrigger.getKey())
                    .withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(cronTrigger.getKey(), trigger);

            QuartzCron quartzCron = new QuartzCron();
            quartzCron.setCronStr(newCron);
            quartzCron.setMinute(minute);

            //更新数据库
            quartzCronDao.update(quartzCron);
        }

        return successTip(newCron);
    }

    public Integer getNow(){
        return quartzCronDao.get().getMinute();
    }
}