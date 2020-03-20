package com.imooc.sm.service.impl;

import com.imooc.sm.dao.LogDao;
import com.imooc.sm.entity.Log;
import com.imooc.sm.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service("logService")
public class LogServiceImpl implements LogService {
    @Autowired
    LogDao logDao;
    public void addSystemLog(Log log) {
        //增加的时间和属于什么类型，可以先设置。
        log.setOprTime(new Date());
        log.setType("System");
        logDao.insert(log);
    }

    public void addLoginLog(Log log) {
        log.setOprTime(new Date());
        log.setType("Login");
        logDao.insert(log);
    }

    public void addOperationLog(Log log) {
        log.setOprTime(new Date());
        log.setType("Operation");
        logDao.insert(log);
    }

    public List<Log> getSystemLog() {
        List<Log> list = logDao.selectByType("System");
        return list;
    }

    public List<Log> getLoginLog() {
        List<Log> list = logDao.selectByType("Login");
        return list;
    }

    public List<Log> getOperationLog() {
        List<Log> list = logDao.selectByType("Operation");
        return list;
    }
}
