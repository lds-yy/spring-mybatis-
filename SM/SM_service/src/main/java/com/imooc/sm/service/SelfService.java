package com.imooc.sm.service;

import com.imooc.sm.entity.Staff;

public interface SelfService {
    Staff login(String account,String password);
    void changePassword(Integer id,String password);
}
