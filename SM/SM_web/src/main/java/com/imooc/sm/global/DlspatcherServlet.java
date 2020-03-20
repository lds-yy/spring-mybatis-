package com.imooc.sm.global;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//Servlet为了进入IOC容器所需要的核心控制器，为其分配控制器
public class DlspatcherServlet extends GenericServlet {
    ApplicationContext appliction;
    @Override
    public void init() throws ServletException {
        appliction = new ClassPathXmlApplicationContext("spring.xml");
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
              //Http才有获取路径的方法
              HttpServletRequest request = (HttpServletRequest)servletRequest;
              HttpServletResponse response = (HttpServletResponse) servletResponse;

              //  status/add.do  add要与方法名一样
              //login.do
        String path = request.getServletPath().substring(1);
        String beanName = null;
        String methodName = null;
        //返回到"/"的长度 否则返回-1
        int index = path.indexOf("/");
        if(index != -1){
            beanName = path.substring(0,index)+"Controller";
            methodName = path.substring(index+1,path.indexOf(".do"));
        }else{
            beanName = "selfController";
            methodName = path.substring(0,path.indexOf(".do"));
        }
            Object o = appliction.getBean(beanName);
        Method method =
                null;
        try {
            method = o.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {

            method.invoke(request,response);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
