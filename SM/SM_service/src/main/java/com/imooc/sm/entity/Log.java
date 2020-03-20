package com.imooc.sm.entity;

import java.util.Date;
//日志处理
public class Log {
    private Date oprTime;
    private String type;
    private String operator;
    private String moudle;
    private String opration;
    private String result;

    public Log() {
    }

    public Date getOprTime() {
        return oprTime;
    }

    public void setOprTime(Date oprTime) {
        this.oprTime = oprTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMoudle() {
        return moudle;
    }

    public void setMoudle(String moudle) {
        this.moudle = moudle;
    }

    public String getOpration() {
        return opration;
    }

    public void setOpration(String opration) {
        this.opration = opration;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Log(Date oprTime, String type, String operator, String moudle, String opration, String result) {
        this.oprTime = oprTime;
        this.type = type;
        this.operator = operator;
        this.moudle = moudle;
        this.opration = opration;
        this.result = result;
    }
}
