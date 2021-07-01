package com.zy.dao.impl;

import com.zy.dao.LoginDao;
import com.zy.entity.Users;
import com.zy.untils.DaoUtlis;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginDaoImpl extends DaoUtlis implements LoginDao {
    /**
     * 登录
     * @param users
     * @return
     */
    @Override
    public List<Users> login(Users users) {
        String sql = "select * from users where uname = ? and upwd = ?";
        Object[] num = {users.getUname(),users.getUpwd()};
        List<Users> list = super.query(sql,num,Users.class);
        return (list!=null&&list.size()>0?list:null);
    }

    /**
     * 注册
     * @param users
     */
    @Override
    public void register(Users users) {
        String sql = "insert into users(uname,upwd,uquestion,uanswer,uemail,utype) values(?,?,?,?,?,?)";
        Object[] num = {users.getUname(),users.getUpwd(),0,1,users.getUemail(),users.getUtype()};
        super.update(sql,num);
    }
}
