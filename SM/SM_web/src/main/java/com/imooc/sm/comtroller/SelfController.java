package com.imooc.sm.comtroller;

import com.imooc.sm.dao.SelfDao;
import com.imooc.sm.entity.Staff;
import com.imooc.sm.service.impl.SelfServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//bean id与web类名一致，符合核心控制器的规范
@Controller("selfController")
public class SelfController {
    @Autowired
    private SelfServiceImpl selfService;
    public void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }
    public void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        Staff staff = selfService.login(account,password);
        if(staff == null){
            response.sendRedirect("toLogin.do");
        }
        //登录账号储存于Session比较安全
        HttpSession session = request.getSession();
        session.setAttribute("USER",staff);
        response.sendRedirect("main.do");
    }
    //退出登录
    public void Loginout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("USER",null);
        response.sendRedirect("toLogin.do");
    }
    //跳转到登录页面
    public void main(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    public void info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../info.jsp").forward(request,response);
    }
    //跳转到修改密码页面
    public void toChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../change_Password.jsp").forward(request,response);
    }
    //修改密码
    public void ChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
        HttpSession httpSession = request.getSession();
        Staff staff = (Staff) httpSession.getAttribute("USER");
        //判断原始密码是否一致
        if(staff.getPassword().equals(password)){
            selfService.changePassword(staff.getId(),password);
        }else{
            response.sendRedirect("toChangePassword.do");
        }
        request.getRequestDispatcher("../Logout.do").forward(request,response);
    }
}
