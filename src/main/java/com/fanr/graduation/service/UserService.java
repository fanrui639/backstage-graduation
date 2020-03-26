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
    User updateUser(User user);

    //注册
    int register(User user);

    //删除注销账号
    int deleteUser(String id);

    //查询单个用户
    User getUser(String id);

    //批量查询用户
    List<User> getAll(Integer page);

    //获得总数
    int getTotal();

    //用户名不重复
    int checkUsername(String username,int id);

    //手机号码不重复
    int checkPhone(String phone,int id);

    //设置用户为管理员
    int setUser(int id,int type);

    //忘记密码中验证手机号
    int verifyPhone(String username,String phone);

    //修改密码
    int updatePassword(String username,String password);

    //更改用户空间使用量
    int updateFile(int id);

}
