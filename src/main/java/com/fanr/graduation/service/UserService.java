package com.fanr.graduation.service;

import com.fanr.graduation.entity.User;

import java.util.List;

/*
 * @author fanr639
 * @since 2019-12-02 14:37:13
 */
public interface UserService {

    //测试
    List<User> queryUserList();

    //登录
    User login(String username,String password);

    //更改用户账号使用情况
    int updateUser(User user);

    //注册
    int register(User user);

    //删除注销账号
    int deleteUser(String id);

    //查询单个用户
    User getUser(String id);

    //批量查询用户
    List<User> getAll(Integer limit,Integer page);

    //获得总数
    int getTotal();

}
