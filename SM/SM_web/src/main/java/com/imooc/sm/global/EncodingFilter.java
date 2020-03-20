package com.imooc.sm.global;

import javax.servlet.*;
import java.io.IOException;

//web请求响应的过滤器（编码一致操作）
public class EncodingFilter implements Filter {
    //设置请求响应编码
        private String encoding = "UTF-8";
    public void init(FilterConfig filterConfig) throws ServletException {
        if (encoding != null) {
            //获取编码格式
            encoding = filterConfig.getInitParameter("ENCODING");
        }
    }
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //设置编码格式
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        //传递请求响应
        filterChain.doFilter(servletRequest,servletResponse);
    }
//销毁时方法
    public void destroy() {
            encoding = null;
    }
}
