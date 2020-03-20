package com.imooc.sm.comtroller;

import com.imooc.sm.entity.Staff;
import com.imooc.sm.service.impl.StaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller("staffController")
public class StaffController {
    @Autowired
    private StaffServiceImpl staffService;
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取人员数组
        List<Staff> list = staffService.getAll();
        //转发到页面显示
        request.setAttribute("list",list);
        //跳转到页面
        request.getRequestDispatcher("../staff_list.jsp").forward(request,response);
    }
    public void toadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //跳转到添加页面
        request.getRequestDispatcher("../staff_add.jsp").forward(request,response);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String idNumber = request.getParameter("idNumber");
        String info = request.getParameter("info");
        //转换为Date类型
        Date bornDate = null;
        bornDate = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("bornDate"));

        Staff staff = new Staff();
        staff.setAccount(account);
        staff.setName(name);
        staff.setSex(sex);
        staff.setIdNumber(idNumber);
        staff.setInfo(info);
        staff.setBornDate(bornDate);

        staffService.add(staff);
        //跳转到首页
        request.getRequestDispatcher("list.do").forward(request,response);
    }
    //跳转到修改页面
    public void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //强转成Integer类型
        Integer id = Integer.parseInt(request.getParameter("id"));
        Staff staff = staffService.get(id);
        request.setAttribute("OBJ",staff);
        request.getRequestDispatcher("../staff_add.jsp").forward(request,response);
    }
    //实现完修改后方便跳转到首页显示
    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String idNumber = request.getParameter("idNumber");
        String info = request.getParameter("info");
        //转换为Date类型
        Date bornDate = null;
        bornDate = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("bornDate"));
        Integer did = Integer.parseInt(request.getParameter("did"));

        Staff staff = new Staff();
        staff.setId(id);
        staff.setAccount(account);
        staff.setName(name);
        staff.setSex(sex);
        staff.setIdNumber(idNumber);
        staff.setInfo(info);
        staff.setBornDate(bornDate);
        staff.setDid(did);

        staffService.edit(staff);
        //跳转到首页
        request.getRequestDispatcher("list.do").forward(request,response);
    }
    public void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        staffService.remove(id);
        response.sendRedirect("list.do");
    }
    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Staff staff = staffService.get(id);
        request.setAttribute("OBJ",staff);
        request.getRequestDispatcher("../staff_detail.jsp").forward(request,response);
    }
}
