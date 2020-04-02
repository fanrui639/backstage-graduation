package com.fanr.graduation.controller;

import com.fanr.graduation.common.*;
import com.fanr.graduation.entity.User;
import com.fanr.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.fanr.graduation.common.Email.sendEmail;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    //登录
    @PostMapping("/login")
    public Result login(String username, String password,String verifyCode, HttpServletRequest request){
        String mypwd = MD5Util.getMd5(password);

        User user = new User();

        HttpSession verifySession = request.getSession();

        String sessionCode = (String)verifySession.getAttribute(VerifyUtil.RANDOMCODEKEY);
        if(verifyCode.equals(sessionCode)){
             user = this.userService.login(username,mypwd);
        }else{
            return ResultUtil.error(500,"请输入正确的验证码！！！");
        }

        if(user != null){
            user.setLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            User result = this.userService.updateUser(user);
        }else{
            return ResultUtil.error(500,"登录失败");
        }
        HttpSession session = request.getSession();
        session.setAttribute("user",user);

        return ResultUtil.success(user);
    }

    //注册
    @PostMapping("/register")
    public Result register(String username,String password,String phone,String email,String verifyCode,HttpSession session){

        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5Util.getMd5(password));
        user.setPhone(phone);
        user.setEmail(email);
        user.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        user.setUserType("0");
        user.setVolume(50);
        user.setFileNum(0);
        user.setLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        user.setUserId(username);

//        session.setAttribute("sendMsgCode","test");    测试的时候用

        String sendMsgCode = (String) session.getAttribute("sendMsgCode");
        if(sendMsgCode != null){
            if(!verifyCode.equals(sendMsgCode)){
                return ResultUtil.error(0,"请输入正确的验证码！");
            }else{
//                System.out.println("开始存入数据库");
                int result = this.userService.register(user);
                if(result > 0){
                    return ResultUtil.success();
                }else{
                    return ResultUtil.error(500,"服务出错！！");
                }
            }
        }else{
            return ResultUtil.error(0,"请输入正确的验证码！");
        }

    }

    //查询所有
    @GetMapping("/getAll")
    public Result getAll(Integer page){
        if (page == null){
            page = 0;
        }
        int total = this.userService.getTotal();
        List<User> list = this.userService.getAll(page);
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

    //修改用户信息
    @PostMapping("/updateUser")
    public Result updateUser(User user,HttpSession session){

        System.out.println("user = " + user);

        User myuser = this.userService.updateUser(user);

        session.setAttribute("user",myuser);

        return ResultUtil.success();
    }

    //发送验证码
    @GetMapping("/sendMsg")
    public Result sendMsg(String phone, HttpSession session){

        String code = createCode(4);

        session.setAttribute("sendMsgCode",code);

//        System.out.println("code = " + code);

        String msg = "【639网盘】您的验证码是" + code + ",５分钟内有效。若非本人操作请忽略此消息。";

        String result = SmsSample.sendMsg(phone,msg);  //调用短信宝发送短信
        if(result.equals("0")){
            Map map = new HashMap();
            map.put("code",0);
            map.put("msg","发送短信成功");

            return ResultUtil.success(map);
        }else{
            return ResultUtil.error(500,"服务出错");
        }

    }

    //页面初始化   根据id获取用户信息
    @GetMapping("/init")
    public Result init(String id,HttpSession session){

        Map map = new HashMap();

        if(id == null || id.equals("")){
            User user = (User) session.getAttribute("user");

            if(user != null){
                User myuser = this.userService.getUser(String.valueOf(user.getId()));
                return ResultUtil.success(myuser);
            }

            map.put("id",-1);
            return ResultUtil.success(map);
        }else{
            User myuser = this.userService.getUser(id);
            return ResultUtil.success(myuser);
        }
    }

    //退出登录
    @GetMapping("/loginOut")
    public Result loginOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return ResultUtil.success();
    }

    //用户名不重复
    @GetMapping("/checkUsername")
    public Result checkUsername(String username,Integer id){
        if(id == null){
            id = 0;
        }
        int result = this.userService.checkUsername(username,id);
        if(result != 0) {
            return ResultUtil.error(500,"用户名已存在");
        }else{
            return ResultUtil.success();
        }
    }

    //注册手机号码不能重复
    @GetMapping("/checkPhone")
    public Result checkPhone(String phone,Integer id){
        if(id == null){
            id = 0;
        }
        int result = this.userService.checkPhone(phone,id);
        if(result != 0) {
            return ResultUtil.error(500,"该手机号以被注册");
        }else{
            return ResultUtil.success();
        }
    }

    //图片验正码
    @GetMapping("/createImg")
    public void createImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            VerifyUtil randomValidateCode = new VerifyUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片
        }catch (Exception e){
            System.out.println("e = " + e);
        }
    }

    //设置用户为管理员
    @GetMapping("/setUser")
    public Result setUser(Integer id,Integer type){

        int result = this.userService.setUser(id,type);

        return ResultUtil.success();
    }

    //忘记密码中发送验证码
    @PostMapping("/verifyPhone")
    public Result verifyPhone(String username,String phone,HttpSession session){
        int verifyPhone = this.userService.verifyPhone(username,phone);
        if(verifyPhone > 0){
            String code = createCode(4);

            session.setAttribute("verifyCode",code);

            String msg = "【639网盘】您的验证码是" + code + ",５分钟内有效。若非本人操作请忽略此消息。";

            String result = SmsSample.sendMsg(phone,msg);  //调用短信宝发送短信
            if(result.equals("0")){
                Map map = new HashMap();
                map.put("code",0);
                map.put("msg","发送短信成功");

                return ResultUtil.success(map);
            }else{
                return ResultUtil.error(500,"服务出错");
            }
        }else{
            return ResultUtil.error(500,"请确认手机号是否属于该账号");
        }
    }

    //忘记密码中验证码
    @PostMapping("/reVerifyCode")
    public Result reVerifyCode(String verifyCode,HttpSession session){
        String code = (String)session.getAttribute("verifyCode");
        if (verifyCode.equals(code)) {
            return ResultUtil.success();
        }else{
            return ResultUtil.error(500,"验证码错误");
        }
    }

    //修改密码
    @PostMapping("/updatePassword")
    public Result updatePassword(String username,String password){
        String mypwd = MD5Util.getMd5(password);
        int result = this.userService.updatePassword(username,mypwd);
        if(result > 0){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(500,"修改密码失败");
        }
    }

    //联系客服
    @GetMapping("/kefu")
    public Result kefu(String title,String msg,HttpSession session) throws GeneralSecurityException, MessagingException {

        User user = (User)session.getAttribute("user");
        String account = user.getEmail();
        account = "2578543184@qq.com";

        sendEmail(title,msg,account);

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
