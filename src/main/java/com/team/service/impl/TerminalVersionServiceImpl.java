package com.team.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.TerminalVersionDao;
import com.team.model.ChannelCard;
import com.team.model.TerminalVersion;
import com.team.service.TerminalVersionService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午6:21 2018/3/20
 */
@Service
@Transactional
public class TerminalVersionServiceImpl implements TerminalVersionService {

    @Autowired
    private TerminalVersionDao terminalVersionDao;

    @Override
    public ResultList list(int page,int rows) {
        PageHelper.startPage(page, rows);

        List<TerminalVersion> list = terminalVersionDao.list();
        PageInfo<TerminalVersion> pageInfo = new PageInfo<TerminalVersion>(list);
        return new ResultList(pageInfo.getTotal(), list);
    }


}