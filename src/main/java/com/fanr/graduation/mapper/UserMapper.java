package com.fanr.graduation.mapper;

import com.fanr.graduation.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> queryUserList();


    //登录
    User login(String username,String password);

    //更改用户账号使用情况
    int updateUser(User user);

    //注册
    int register(User user);

    //删除注销用户
    int deleteUser(String id);

    //查询单个用户
    User getUser(String id);

    //批量查询用户
    List<User> getAll(Integer page);

    //获得总数
    int getTotal();

    //用户名不重复
    int checkUsername(String username,int id);

    //手机号不重复
    int checkPhone(String phone,int id);

    //设置用户为管理员
    int setUser(int id,int type);

    //忘记密码中验证手机
    int verifyPhone(String username,String phone);

    //修改密码
    int updatePassword(String username,String password);

    //更改用户文件使用量
    int updateFile(int num,int used,int id);

}