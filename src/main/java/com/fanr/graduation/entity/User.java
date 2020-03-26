package com.fanr.graduation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {

    //主键
    private int id;

    //用户名
    private String username;

    //密码
    private String password;

    //创建时间
    private String create_time;

    //文件数量
    private int file_num;

    //空间容量
    private int volume;

    //空间使用量
    private int used;

    //用户类型
    private String usertype;

    //上次登录时间
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String login_time;

    //手机号
    private String phone;

    //电子邮箱
    private String email;

    //手机验证码
    private String verifyCoe;


    //无参构造函数
    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateTime() {
        return create_time;
    }

    public void setCreateTime(String createTime) {
        this.create_time = createTime;
    }

    public int getFileNum() {
        return file_num;
    }

    public void setFileNum(int fileNum) {
        this.file_num = file_num;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public String getUserType() {
        return usertype;
    }

    public void setUserType(String userType) {
        this.usertype = userType;
    }

    public String getLoginTime() {
        return login_time;
    }

    public void setLoginTime(String loginTime) {
        this.login_time = loginTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerifyCoe() {
        return verifyCoe;
    }

    public void setVerifyCoe(String verifyCoe) {
        this.verifyCoe = verifyCoe;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime='" + create_time + '\'' +
                ", file_num=" + file_num +
                ", volume='" + volume + '\'' +
                ", used='" + used + '\'' +
                ", userType='" + usertype + '\'' +
                ", login_time='" + login_time + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", verifyCode='" + verifyCoe +'\'' +
                '}';
    }
}
