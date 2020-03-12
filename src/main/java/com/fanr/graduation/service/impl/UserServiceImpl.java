package com.fanr.graduation.service.impl;

import com.fanr.graduation.entity.User;
import com.fanr.graduation.mapper.UserMapper;
import com.fanr.graduation.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> queryUserList() {
        return this.userMapper.queryUserList();
    }

    @Override
    public User login(String username, String password) {
        return this.userMapper.login(username,password);
    }

    @Override
    public int updateUser(User user) {
        return this.userMapper.updateUser(user);
    }


    @Override
    public int register(User user){
        return this.userMapper.register(user);
    }

    @Override
    public int deleteUser(String id) {
        return this.userMapper.deleteUser(id);
    }

    @Override
    public User getUser(String id) {
        return this.userMapper.getUser(id);
    }

    @Override
    public List<User> getAll(Integer limit, Integer page) {
        return this.userMapper.getAll(limit,page);
    }

    @Override
    public int getTotal() {
        return this.userMapper.getTotal();
    }

    @Override
    public int checkUsername(String username,int id) {
        return this.userMapper.checkUsername(username,id);
    }

    @Override
    public int setUser(int id,int type) {
        return this.userMapper.setUser(id,type);
    }

    @Override
    public int verifyPhone(String username, String phone) {
        return this.userMapper.verifyPhone(username,phone);
    }

    @Override
    public int updatePassword(String username, String password) {
        return this.userMapper.updatePassword(username,password);
    }


}
