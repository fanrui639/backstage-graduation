package com.fanr.graduation.service.impl;

import com.fanr.graduation.entity.File;
import com.fanr.graduation.mapper.FileMapper;
import com.fanr.graduation.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileServiceImple implements FileService {

    @Resource
    private FileMapper fileMapper;

    @Override
    public List<File> queryFileList() {
        return this.fileMapper.queryFileList();
    }
}
