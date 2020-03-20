package com.imooc.sm.service;

import com.imooc.sm.entity.Log;

import java.util.List;

//调用DAO功能，并增加所需的逻辑语句
public interface LogService {
    void addSystemLog(Log log);
    void addLoginLog(Log log);
    void addOperationLog(Log log);

    List<Log> getSystemLog();
    List<Log> getLoginLog();
    List<Log> getOperationLog();
}
