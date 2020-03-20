package com.imooc.sm.dao;

import com.imooc.sm.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;
//配置自动映射
@Repository("departmentDao")
public interface DepartmentDao {
    void insert(Department department);
    void delete(Integer id);
    void update(Department department);
    Department selectById(Integer id);
    List<Department> selectAll();
}
