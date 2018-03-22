package com.team.service;

import com.team.model.TerminalVersion;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午6:20 2018/3/20
 */
public interface TerminalVersionService {

    ResultList list(int page,int rows);

    ReturnMsg saveOrUpdate(TerminalVersion terminalVersion);
}
