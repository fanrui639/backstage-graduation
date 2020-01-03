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

    //查询总条数
    long count();

    //分页查询所有
    List<MyFile> queryAllByLimit(String start,String length);

    //通过实体作为筛选条件查询
    List<MyFile> queryAll();

    //新增所有列
    int insert(MyFile myFile);

    //修改
    int update(MyFile myFile);

    //删除
    int deleteById(String id);

}
