package com.fanr.graduation.mapper;

import com.fanr.graduation.entity.MyFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MyFileMapper {

//    @Select("select * from file")
    List<MyFile> queryFileList(int id,String page);

    //上传单个文件
    int uploadFile(MyFile file);

    //根据id查文件信息
    MyFile queryById(String fileId);

}
