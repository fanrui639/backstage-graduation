package com.fanr.graduation.service.impl;

import com.fanr.graduation.entity.MyFile;
import com.fanr.graduation.mapper.MyFileMapper;
import com.fanr.graduation.service.MyFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyFileServiceImple implements MyFileService {

    @Resource
    private MyFileMapper myFileMapper;

    //显示用户所有文件
    @Override
    public List<MyFile> queryFileList(int id, String page) {
        return this.myFileMapper.queryFileList(id,page);
    }

    //上传单个文件
    @Override
    public int uploadFile(MyFile file) {
        return myFileMapper.uploadFile(file);
    }

    //上传多个文件
    @Override
    public int uploadFiles(List<MyFile> list) {
        return 0;
    }

    //根据id查
    @Override
    public MyFile queryById(String fileId) {
        return this.myFileMapper.queryById(fileId);
    }
}
