package com.imooc.sm.global;

import com.imooc.sm.entity.Log;
import com.imooc.sm.entity.Staff;
import com.imooc.sm.service.impl.LogServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@Aspect
public class LogAdvice {
    @Autowired
    private LogServiceImpl logService;
    //操作日志，屏蔽登录日志还有to为前缀的方法
    @After("execution(* com.imooc.sm.comtroller.*.*(..)) && !execution(* com.imooc.sm.comtroller.SelfController.*(..)) && !execution(* com.imooc.sm.comtroller.*.to*(..))")
    public void operationLog(JoinPoint joinPoint){
        Log log = new Log();
        //joinPoint 连接点获得类名 方法名
        log.setMoudle(joinPoint.getTarget().getClass().getName());
        log.setOperator(joinPoint.getSignature().getName());
        HttpServletRequest httpServletRequest = (HttpServletRequest)joinPoint.getArgs()[0];
        HttpSession session = httpServletRequest.getSession();
        Staff staff = (Staff)session.getAttribute("USER");
        log.setOpration(staff.getAccount());
        log.setResult("成功");
        logService.addOperationLog(log);
    }
    //异常报错执行
    @AfterThrowing(throwing = "e",pointcut = "execution(* com.imooc.sm.comtroller.*.*(..)) && !execution(* com.imooc.sm.comtroller.SelfController.*(..))")
    public void SystemLog(JoinPoint joinPoint,Throwable e){
        Log log = new Log();
        //joinPoint 连接点获得类名 方法名
        log.setMoudle(joinPoint.getTarget().getClass().getName());
        log.setOperator(joinPoint.getSignature().getName());
        HttpServletRequest httpServletRequest = (HttpServletRequest)joinPoint.getArgs()[0];
        HttpSession session = httpServletRequest.getSession();
        Staff staff = (Staff)session.getAttribute("USER");
        log.setOpration(staff.getAccount());
        //异常信息
        log.setResult("异常:"+e.getClass().getSimpleName());
        //添加日志
        logService.addSystemLog(log);
    }
    @After("execution(* com.imooc.sm.comtroller.SelfController.Login(..))")
    public void LoginLog(JoinPoint joinPoint){
        Log log = new Log();
        //joinPoint 连接点获得类名 方法名
        log.setMoudle(joinPoint.getTarget().getClass().getName());
        log.setOperator(joinPoint.getSignature().getName());
        HttpServletRequest httpServletRequest = (HttpServletRequest)joinPoint.getArgs()[0];
        HttpSession session = httpServletRequest.getSession();
        Staff staff = (Staff)session.getAttribute("USER");
        if(staff == null){
            log.setOpration(httpServletRequest.getParameter("account"));
            log.setResult("账户不匹配");
        }
        log.setOpration(staff.getAccount());
        log.setResult("成功");
        logService.addLoginLog(log);
    }
    @Before("execution(* com.imooc.sm.comtroller.SelfController.Loginout(..))")
    public void LogoutLog(JoinPoint joinPoint){
        Log log = new Log();
        //joinPoint 连接点获得类名 方法名
        log.setMoudle(joinPoint.getTarget().getClass().getName());
        log.setOperator(joinPoint.getSignature().getName());
        HttpServletRequest httpServletRequest = (HttpServletRequest)joinPoint.getArgs()[0];
        HttpSession session = httpServletRequest.getSession();
        Staff staff = (Staff)session.getAttribute("USER");
        log.setOpration(staff.getAccount());
        log.setResult("退出");
        logService.addLoginLog(log);
    }
}
