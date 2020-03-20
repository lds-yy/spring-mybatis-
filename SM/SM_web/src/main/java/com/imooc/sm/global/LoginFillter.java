package com.imooc.sm.global;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFillter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpservletResponse = (HttpServletResponse)servletResponse;

        String path = httpServletRequest.getServletPath();
        //转换大小写，并且判断其是不是login的类，是则通过
        if(path.toLowerCase().indexOf("Login" ) != -1){
            filterChain.doFilter(httpServletRequest,httpservletResponse);
        }else{
            //判断session是否有账号的信息，没有则跳转到登录页面
            HttpSession httpSession = httpServletRequest.getSession();
            Object o = httpSession.getAttribute("USER");
            if(o==null){
                httpservletResponse.sendRedirect(httpServletRequest.getContextPath()+"/toLogin.do");
            }else{
                filterChain.doFilter(httpServletRequest,httpservletResponse);
            }
        }
    }

    public void destroy() {

    }
}
