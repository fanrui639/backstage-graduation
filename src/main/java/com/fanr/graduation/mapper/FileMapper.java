package com.fanr.graduation.mapper;

import com.fanr.graduation.entity.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {

//    @Select("select * from file")
    List<File> queryFileList();

}
