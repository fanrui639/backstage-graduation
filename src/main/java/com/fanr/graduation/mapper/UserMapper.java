package com.fanr.graduation.mapper;

import com.fanr.graduation.entity.User;
import org.apache.ibatis.annotations.Mapper;

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
    List<User> getAll(Integer limit,Integer page);

    //获得总数
    int getTotal();

    //用户名不重复
    int checkUsername(String username,int id);

    //设置用户为管理员
    int setUser(int id,int type);

}