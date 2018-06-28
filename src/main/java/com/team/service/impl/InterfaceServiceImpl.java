package com.team.service.impl;

import com.google.gson.Gson;
import com.team.dao.CostDayDao;
import com.team.dao.FlowBalanceDao;
import com.team.dao.TerminalChargeRecordDao;
import com.team.dao.TerminalDao;
import com.team.dao.auth.OperationLogDao;
import com.team.model.CostDay;
import com.team.model.FlowBalance;
import com.team.model.Terminal;
import com.team.model.TerminalChargeRecord;
import com.team.model.auth.OperationLog;
import com.team.service.InterfaceService;
import com.team.service.TerminalChargeService;
import com.team.util.CommonUtil;
import com.team.util.IPUtils;
import com.team.util.LogManager;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author : wuzhiheng
 * @Description :   对外开放的接口
 * @Date Created in 下午5:30 2018/6/21
 */
@Service
@Transactional
public class InterfaceServiceImpl extends BaseService implements InterfaceService {

    @Autowired
    private CostDayDao costDayDao;
    @Autowired
    private TerminalChargeRecordDao terminalChargeRecordDao;
    @Autowired
    private TerminalDao terminalDao;
    @Autowired
    private FlowBalanceDao flowBalanceDao;


    @Override
    public ReturnMsg qtb(Integer tsid) {
        FlowBalance flowBalance = flowBalanceDao.findByTsid(tsid);
        return successTip(flowBalance);
    }

    @Override
    public ReturnMsg qti(Integer tsid) {
        //查询终端流水
        List<CostDay> costDayList = costDayDao.getCostDayByTsid(tsid);
        //查询终端充值记录
        List<TerminalChargeRecord> terminalChargeRecordList = terminalChargeRecordDao.list(tsid);

        Map<String,Object> map = new HashMap<>();
        map.put("flowRecord",costDayList);
        map.put("chargeRecord",terminalChargeRecordList);

        return successTip(map);
    }

    @Override
    public ReturnMsg tCharge(TerminalChargeRecord terminalChargeRecord) {

        Terminal terminal = terminalDao.getById(terminalChargeRecord.getTsid());
        if(terminal==null){
            return errorTip("充值失败，终端不存在");
        }

        //获取源流量和日期参数
        FlowBalance flowBalance = flowBalanceDao.findByTsid(terminalChargeRecord.getTsid());
        if(flowBalance==null){
            flowBalance = new FlowBalance();
            flowBalance.setTsid(terminalChargeRecord.getTsid());
        }

        //1.处理流量，单位G
        if(terminalChargeRecord.getChargeFlow()!=null){
            Long allowFlow = flowBalance.getAllowFlow()==null?0L:flowBalance.getAllowFlow();
            if(allowFlow<0){
                allowFlow=0L;
            }
            flowBalance.setAllowFlow(allowFlow+terminalChargeRecord.getChargeFlow()*1024*1024);
            terminalChargeRecord.setChargeFlow(terminalChargeRecord.getChargeFlow()*1024*1024);
        }

        //2.处理天数，单位天
        if(terminalChargeRecord.getChargeDate()!=null){
            Date validityDate = flowBalance.getValidityDate()==null?new Date():flowBalance.getValidityDate();
            if(validityDate.before(new Date())){
                validityDate = new Date();
            }
            flowBalance.setValidityDate(new Date(validityDate.getTime()+terminalChargeRecord.getChargeDate()*24*60*60*1000));
        }

        //3.填充充值数据
        terminalChargeRecord.setAllowFlow(flowBalance.getAllowFlow());
        terminalChargeRecord.setValidityDate(flowBalance.getValidityDate());
        terminalChargeRecord.setCreateDate(new Date());

        //4.保存终端信息
        if(flowBalance.getId()!=null){
            flowBalanceDao.update(flowBalance);
        }else {
            flowBalanceDao.save(flowBalance);
        }

        //5.保存充值流水
        int count = terminalChargeRecordDao.save(terminalChargeRecord);

        return count>0?successTip():errorTip();
    }

}