package com.zy.service.impl;

import com.zy.dao.UsersMapper;
import com.zy.entity.Users;
import com.zy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersMapper usersDao;

    /**
     * 查询所有管理员
     *
     * @return
     */
    @Override
    public List<Users> usersManager() {
        return usersDao.UserManager();
    }

    /**
     * 查询要修改的管理员信息
     *
     * @param id
     * @return
     */
    @Override
    public List<Users> editUser(int id) {
        return usersDao.editUser(id);
    }

    /**
     * 更新管理员信息
     *
     * @param users
     */
    @Override
    public void updateUser(Users users) {
        usersDao.updateUser(users);
    }

    /**
     * 删除管理员
     *
     * @param id
     */
    @Override
    public void deleteUser(int id) {
        usersDao.deleteUser(id);
    }

    @Override
    public void addUser(Users users) {
        usersDao.addUser(users);
    }
}
