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
    public List<MyFile> queryFileList(int id, int page) {
        return this.myFileMapper.queryFileList(id,page);
    }

    @Override
    public int getFileNum(int id){
        return this.myFileMapper.getFileNum(id);
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

    //查找同名文件id
    @Override
    public MyFile getFileID(String filename, int userId) {
        return this.myFileMapper.getFileID(filename,userId);
    }

    @Override
    public int deleteById(int id) {
        return this.myFileMapper.deleteById(id);
    }

    @Override
    public List<MyFile> getShare(Integer page) {
        return this.myFileMapper.getShare(page);
    }

    @Override
    public int getTotal(){
        return this.myFileMapper.getTotal();
    }

    @Override
    public List<MyFile> getShareBySearch(String searchContext) {
        return this.myFileMapper.getShareBySearch(searchContext);
    }

    //查找单个文件
    @Override
    public MyFile getFileById(int id) {
        return this.myFileMapper.getFileById(id);
    }

    //删除文件
    @Override
    public int deleteFile(Integer id) {
        return this.myFileMapper.deleteFile(id);
    }

    //分享文件
    @Override
    public int shareFile(int id, String shareCode) {
        return this.myFileMapper.shareFile(id,shareCode);
    }

    @Override
    public int downShareFile(int id, String code) {
        return this.myFileMapper.downShareFile(id,code);
    }

}
