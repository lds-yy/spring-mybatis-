package com.imooc.sm.service.impl;

import com.imooc.sm.dao.DepartmentDao;
import com.imooc.sm.entity.Department;
import com.imooc.sm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//代表业务层
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;
    public void add(Department department) {
        departmentDao.insert(department);
    }

    public void remove(Integer id) {
        departmentDao.delete(id);
    }

    public void edit(Department department) {
        departmentDao.update(department);
    }

    public Department get(Integer id) {
        Department department = departmentDao.selectById(id);
        return department;
    }

    public List<Department> getAll() {
        List<Department> list = departmentDao.selectAll();
        return list;
    }
}
