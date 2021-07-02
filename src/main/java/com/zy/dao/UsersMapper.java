package com.zy.dao;

import com.zy.entity.Users;

import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    /**
     * 查询所有用户
     *
     * @return
     */
    public List<Users> UserManager();

    /**
     * 查询要编辑的管理员
     *
     * @param id
     * @return
     */
    public List<Users> editUser(int id);

    public Users selectUser(String name);

    /**
     * 更新管理员
     *
     * @param users
     */
    public void updateUser(Users users);

    /**
     * 删除管理员
     *
     * @param id
     */
    public void deleteUser(int id);

    public void addUser(Users users);

    /**
     *验证用户登录
     * @param users
     * @return
     */
    public List<Users> login(Users users);

    /**
     * 用户注册
     * @param users
     */
    public void register(Users users);
}