package com.fanr.graduation.controller;

import com.fanr.graduation.entity.File;
import com.fanr.graduation.entity.User;
import com.fanr.graduation.mapper.UserMapper;
import com.fanr.graduation.common.MD5Util;
import com.fanr.graduation.service.FileService;
import com.fanr.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class QuickStartController {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @GetMapping("/queryUser")
    public List<User> queryUser(){
        System.out.println("已经进入方法了====================================================");
        List<User> users = this.userService.queryUserList();
        System.out.println("users.size() = " + users.size());
        System.out.println("方法结束了====================================================");
        return users;
    }

    @GetMapping("/queryFile")
    public List<File> queryFile(){
        System.out.println("已经进入方法了====================================================");
        List<File> list = this.fileService.queryFileList();
        System.out.println("方法结束====================");
        return list;
    }

    @GetMapping("/quick")
    public String quick() { return "springboot 访问成功!";}


}
