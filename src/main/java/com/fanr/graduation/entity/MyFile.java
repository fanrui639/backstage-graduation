package com.fanr.graduation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MyFile {

    //文件或文件夹标号
    private int id;

    //文件名称
    private String fileName;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    //文件大小
    private int size;

    //文件路径
    private String path;

    //用户id
    private int userId;

    //文件类型
    private String fileType;

    //分享数
    private int shareNum;

    //文件是共享类型还是私密类型
    private int fileProperty;

    //提取码
    private String code;

    public MyFile() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getShareNum() {
        return shareNum;
    }

    public void setShareNum(int shareNum) {
        this.shareNum = shareNum;
    }

    public int getFileProperty() {
        return fileProperty;
    }

    public void setFileProperty(int fileProperty) {
        this.fileProperty = fileProperty;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", createTime=" + createTime +
                ", size='" + size + '\'' +
                ", path='" + path + '\'' +
                ", userId=" + userId +
                ", fileType='" + fileType + '\'' +
                ", shareNum=" + shareNum +
                ", fileProperty=" + fileProperty +
                ", code='" + code + '\'' +
                '}';
    }
}
