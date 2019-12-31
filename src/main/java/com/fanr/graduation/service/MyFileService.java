package com.fanr.graduation.service;

import com.fanr.graduation.entity.MyFile;

import java.util.List;

public interface MyFileService {

    //显示用户所有文件
    List<MyFile> queryFileList(int id,String page);

    //上传单个文件
    int uploadFile(MyFile file);

    //上传多个文件
    int uploadFiles(List<MyFile> list);

    //根据id查询
    MyFile queryById(String fileId);

}
