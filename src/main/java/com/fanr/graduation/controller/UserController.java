package com.fanr.graduation.controller;

import com.fanr.graduation.common.MD5Util;
import com.fanr.graduation.common.Result;
import com.fanr.graduation.common.ResultUtil;
import com.fanr.graduation.common.SmsSample;
import com.fanr.graduation.entity.User;
import com.fanr.graduation.mapper.UserMapper;
import com.fanr.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    //登录
    @PostMapping("/login")
    public Result login(String username, String password, HttpServletRequest request){
        String mypwd = MD5Util.getMd5(password);
        User user = this.userService.login(username,mypwd);
        if(user != null){
            user.setLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            int result = this.userService.updateUser(user);
        }else{
            return ResultUtil.error(0,"登录失败");
        }
        HttpSession session = request.getSession();
        session.setAttribute("user",user);

        return ResultUtil.success(user);
    }

    //注册
    @PostMapping("/register")
    public Result register(String username,String password,String phone,String email,String verifyCoe,HttpSession session){

        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5Util.getMd5(password));
        user.setPhone(phone);
        user.setEmail(email);
        user.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        user.setUserType("1");
        user.setVolume("50");
        user.setFileNum(0);
        user.setLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        user.setUserId(username);

        String sendMsgCode = (String) session.getAttribute("sendMsgCode");
        if(sendMsgCode != null){
            if(!verifyCoe.equals(sendMsgCode)){
                return ResultUtil.error(0,"请输入正确的验证码！");
            }else{
                int result = this.userService.register(user);
            }
        }else{
            return ResultUtil.error(0,"请输入正确的验证码！");
        }

        return ResultUtil.success();

    }

    //查询所有
    @GetMapping("/getAll")
    public Result getAll(Integer limit,Integer page){
        if (limit == null){
            limit = 0;
        }
        if (page == null){
            page = 10;
        }
        int total = this.userService.getTotal();
        List<User> list = this.userService.getAll(limit,page);
        return ResultUtil.success(total,list);
    }

    //删除 注销 用户
    @DeleteMapping("/deleteUser/{id}")
    public Result deleteUser(@PathVariable String id){
        int result = this.userService.deleteUser(id);
        return ResultUtil.success();
    }

    //查询单个用户
    @GetMapping("/getUser")
    public Result getUser(String id){
        User user = this.userService.getUser(id);
        return ResultUtil.success(user);
    }

//    //修改用户
//    @GetMapping("/updateUser")
//    public Result updateUser(String id){
//        return ResultUtil.success();
//    }

    //编辑用户信息
    @PostMapping("/editUser")
    public Result editUser(User user){
        return ResultUtil.success();
    }

    //发送验证码
    @GetMapping("/sendMsg")
    public Result sendMsg(String phone, HttpSession session){

        String code = createCode(4);

        session.setAttribute("sendMsgCode",code);

        System.out.println("code = " + code);

        String msg = "【便利网盘】您的验证码是" + code + ",５分钟内有效。若非本人操作请忽略此消息。";

        SmsSample.sendMsg(phone,msg);  //调用短信宝发送短信


        Map map = new HashMap();
        map.put("code",0);
        map.put("msg","发送短信成功");

        return ResultUtil.success(map);

    }

    //页面初始化
    @GetMapping("/init")
    public Result init(HttpServletRequest request){
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        if(user != null){
            return ResultUtil.success(user);
        }
        Map map = new HashMap();
        map.put("id",-1);
        return ResultUtil.success(map);
    }

    //退出登录
    @GetMapping("/loginOut")
    public Result loginOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return ResultUtil.success();
    }


    //根据长度生成随机数
    public String createCode(int len) {
        String s = "";
        for (int i = 1; i <= len; i++) {
            int k = new Random().nextInt(10);  //含下不含上
            s = s + k;
        }
        return s;
    }


}
