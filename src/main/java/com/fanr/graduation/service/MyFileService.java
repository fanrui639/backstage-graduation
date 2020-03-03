package com.fanr.graduation.service;

import com.fanr.graduation.entity.MyFile;

import java.util.List;

public interface MyFileService {

    //显示用户所有文件
    List<MyFile> queryFileList(int id,int page);

    //上传单个文件
    int uploadFile(MyFile file);

    //上传多个文件
    int uploadFiles(List<MyFile> list);

    //根据id查询
    MyFile queryById(String fileId);

    //查找同名文件id
    MyFile getFileID(String filename,int userId);

    //根据id删除文件
    int deleteById(int id);

    //显示共享文件
    List<MyFile> getShare();

    //查找单个文件
    MyFile getFileById(int id);

    //删除文件
    int deleteFile(Integer id);

    //分享文件
    int shareFile(int id,String shareCode);

}
