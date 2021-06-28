package com.zy.dao.impl;

import com.zy.dao.UsersDao;
import com.zy.entity.Users;
import com.zy.untils.DaoUtlis;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersDaoImpl extends DaoUtlis implements UsersDao {
    /**
     * 查询所有管理员信息
     * @return
     */
    @Override
    public List<Users> UserManager() {
        String sql = "select * from users";
        List<Users> usersList = super.query(sql,null,Users.class);
        return (usersList!=null && usersList.size()>0 ? usersList : null);
    }

    /**
     * 查询要修改的管理员
     * @param id
     * @return
     */
    @Override
    public List<Users> editUser(int id) {
        String sql = "select * from users where uid = ?";
        Object[] num = {id};
        List<Users> usersList = super.query(sql,num,Users.class);
        return (usersList!=null && usersList.size()>0 ? usersList : null);
    }

    @Override
    public Users selectUser(String name) {
        String sql = "select * from users where uname = ?";
        Object[] num = {name};
        List<Users> usersList = super.query(sql,num,Users.class);
        return (usersList!=null && usersList.size()>0 ? usersList.get(0) : null);
    }


    /**
     * 保存修改好的管理员
     * @param users
     */
    @Override
    public void updateUser(Users users) {
        String sql = "UPDATE users set uname = ?,upwd = ?,uemail = ?,utype = ? where uid = ?";
        Object[] num = {users.getUname(),users.getUpwd(),users.getUemail(),users.getUtype(),users.getUid()};
        super.update(sql,num);
    }

    @Override
    public void deleteUser(int id) {
        String sql = "delete from users where uid = ?";
        Object[] num = {id};
        super.update(sql,num);
    }

    @Override
    public void addUser(Users users) {
        String sql = "insert into users(uname,upwd,uquestion,uanswer,uemail,utype) values(?,?,?,?,?,?)";
        Object[] num = {users.getUname(),users.getUpwd(),0,1,users.getUemail(),users.getUtype()};
        super.update(sql,num);
    }
}
