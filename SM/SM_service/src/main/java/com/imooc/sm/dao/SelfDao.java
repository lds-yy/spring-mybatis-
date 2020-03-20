package com.imooc.sm.dao;

import com.imooc.sm.entity.Staff;
import org.springframework.stereotype.Repository;

@Repository
public interface SelfDao {
    Staff selectByAccount(String account);
}
