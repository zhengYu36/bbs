package com.zy.service.impl;

import com.zy.dao.UsersMapper;
import com.zy.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl{

    @Autowired
    UsersMapper usersDao;

    /**
     * 查询所有管理员
     *
     * @return
     */
    public List<Users> usersManager() {
        return usersDao.UserManager();
    }

    /**
     * 查询要修改的管理员信息
     *
     * @param id
     * @return
     */
    public List<Users> editUser(int id) {
        return usersDao.editUser(id);
    }

    /**
     * 更新管理员信息
     *
     * @param users
     */
    public void updateUser(Users users) {
        usersDao.updateUser(users);
    }

    /**
     * 删除管理员
     *
     * @param id
     */
    public void deleteUser(int id) {
        usersDao.deleteUser(id);
    }


    public void addUser(Users users) {
        usersDao.addUser(users);
    }
}
