package com.zy.service.impl;

import com.zy.dao.UsersMapper;
import com.zy.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl {

    @Autowired
    UsersMapper loginDao;

    public List<Users> login(Users users) {
        return loginDao.login(users);
    }

    public void register(Users users) {
        loginDao.register(users);
    }
}
