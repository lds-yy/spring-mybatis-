package com.imooc.sm.comtroller;

import com.imooc.sm.entity.Department;
import com.imooc.sm.entity.Log;
import com.imooc.sm.service.LogService;
import com.imooc.sm.service.impl.LogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller("logController")
public class LogController {
    @Autowired
    private LogService logService;
    public void operationLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> list = logService.getOperationLog();
        request.setAttribute("Type","操作");
        request.setAttribute("list",list);
        request.getRequestDispatcher("../log_list.jsp").forward(request,response);
    }
    public void loginLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> list = logService.getLoginLog();
        request.setAttribute("Type","登录");
        request.setAttribute("list",list);
        request.getRequestDispatcher("../log_list.jsp").forward(request,response);
    }
    public void systemLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> list = logService.getSystemLog();
        request.setAttribute("Type","系统异常");
        request.setAttribute("list",list);
        request.getRequestDispatcher("../log_list.jsp").forward(request,response);
    }
}