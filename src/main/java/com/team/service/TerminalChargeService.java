package com.team.service;

import com.team.model.TerminalChargeRecord;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description : 终端充值记录
 * @Date Created in 下午4:51 2018/3/26
 */
public interface TerminalChargeService {

    /**
     * 充值
     * @param record
     * @return
     */
    ReturnMsg charge(TerminalChargeRecord record);

    ResultList list(Integer tsid, Date startDate,Date endDate,Integer departmentId,int page,int rows);

}
