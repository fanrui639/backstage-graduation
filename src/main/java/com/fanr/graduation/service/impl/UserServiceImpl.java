package com.fanr.graduation.service.impl;

import com.fanr.graduation.entity.User;
import com.fanr.graduation.mapper.MyFileMapper;
import com.fanr.graduation.mapper.UserMapper;
import com.fanr.graduation.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MyFileMapper myFileMapper;

    @Override
    public List<User> queryUserList() {
        return this.userMapper.queryUserList();
    }

    @Override
    public User login(String username, String password) {
        return this.userMapper.login(username,password);
    }

    @Override
    public User updateUser(User user) {
        int result = this.userMapper.updateUser(user);
        String id = String.valueOf(user.getId());
        return this.userMapper.getUser(id);
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
    public List<User> getAll(Integer page) {
        return this.userMapper.getAll(page);
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
    public int checkPhone(String phone,int id) {
        return this.userMapper.checkPhone(phone,id);
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

    @Override
    public int updateFile(int id) {
        int num = this.myFileMapper.getFileNum(id);
        Integer used = this.myFileMapper.getFileVolume(id);
        int usedd = used == null ? 0 : used;
        return this.userMapper.updateFile(num,usedd,id);
    }


}
