package com.imooc.sm.comtroller;

import com.imooc.sm.entity.Department;
import com.imooc.sm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
//servlet和页面是同步执行的。
@Controller("departmentController")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    //查询所有数据
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> list = departmentService.getAll();
        request.setAttribute("list",list);
        request.getRequestDispatcher("../department_list.jsp").forward(request,response);
    }
    //定向到添加页面
    public void toAdd(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../department_add.jsp").forward(request,response);
    }
    //往数据库添加数据
    public void add(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");

        Department department = new Department();
        department.setName(name);
        department.setAddress(address);
        //list.do就是其servlet路径
        departmentService.add(department);
        response.sendRedirect("list.do");
    }
    public void toEdit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Department department = departmentService.get(id);
        request.setAttribute("OBJ",department);

        request.getRequestDispatcher("../department_add.jsp").forward(request,response);
    }
    //往数据库添加数据
    public void edit(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");

        Department department = new Department();
        department.setId(id);
        department.setName(name);
        department.setAddress(address);
        //list.do就是其servlet路径
        departmentService.edit(department);
        response.sendRedirect("list.do");
    }
    public void remove(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        //list.do就是其servlet路径
        departmentService.remove(id);
        response.sendRedirect("list.do");
    }
}
