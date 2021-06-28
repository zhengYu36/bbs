package com.zy.service.impl;

import com.zy.dao.LoginDao;
import com.zy.dao.impl.LoginDaoImpl;
import com.zy.entity.Users;
import com.zy.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDao loginDao;

    @Override
    public List<Users> login(Users users) {
        List<Users> users1 = loginDao.login(users);
        return users1;
    }

    @Override
    public void register(Users users) {
        loginDao.register(users);
    }
}
