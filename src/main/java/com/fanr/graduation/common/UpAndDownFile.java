package com.fanr.graduation.common;

import com.fanr.graduation.entity.MyFile;
import com.fanr.graduation.service.MyFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fanr639
 * 文件长传下载类
 */
public class UpAndDownFile {

    private static final Logger logger = LoggerFactory.getLogger(UpAndDownFile.class);

    @Autowired
    private static MyFileService myFileService;


    //单个文件进行上传
    public static Map upload(String filePath,MultipartFile file,int userId) throws IOException {
        long startTime = System.currentTimeMillis();   //获取开始时间

        Map map = new HashMap();
        boolean stat = false;
//        String pathname = "G:\\file\\";
        //判断上传文件的保存目录是否存在
        File targetFile = new File(filePath);
        if(!targetFile.exists() && !targetFile.isDirectory()){
            System.out.println(filePath+"  目录不存在，需要创建");
            //创建目录
            targetFile.mkdirs();
        }

        //如果上传空文件
        if (file.isEmpty()) {
            map.put("info", "请选择有效文件");
            map.put("stat", stat);
            return map;
        }
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath + "//" +
                new File(file.getOriginalFilename())));
        bos.write(file.getBytes());
        bos.flush();
        bos.close();

//        MyFile myfile = new MyFile();
//        myfile.setCode("0");
//        myfile.setCreateTime(new Date());
//        myfile.setFileName(file.getOriginalFilename());
////        myfile.setFileProperty();
//        myfile.setFileType(file.getName());
//        myfile.setPath(filePath);
//        myfile.setShareNum(0);
//        myfile.setSize((int) file.getSize());
//        myfile.setUserId(userId);
//        myFileService.uploadFile(myfile);

//        int result = saveFile(file,filePath,userId);

//        if(result > 0){
//            stat = true;
//        }

        map.put("info", "上传成功！");
        map.put("stat", stat);
        map.put("fileName", file.getOriginalFilename());
        map.put("fileType",file.getName());
        map.put("fileSize",file.getSize());

        long endTime = System.currentTimeMillis(); //获取结束时间

//        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
        return map;
    }

    //多个文件上传
    public static Map uploads(String filePath,MultipartFile[] files,int userId) throws IOException {
        Map map = new HashMap();
        boolean stat = false;

//        String pathname = "G:\\file\\";
        //判断上传文件的保存目录是否存在
        File targetFile = new File(filePath);
        if(!targetFile.exists() && !targetFile.isDirectory()){
            System.out.println(filePath + "目录不存在，需要创建");
            //创建目录
            targetFile.mkdirs();
        }

        if (files.length < 1) {
            map.put("info", "请选择有效文件");
            map.put("stat", stat);
            return map;
        }

        int sum = 0;  //上传文件成功数

        for (MultipartFile file : files) {
            byte[] bytes = file.getBytes();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream( filePath +
                    new File(file.getOriginalFilename())));
            bos.write(bytes);
            bos.flush();
            bos.close();

            sum += saveFile(file,filePath,userId);

        }
        stat = true;
        map.put("info", "上传成功！");
        map.put("stat", stat);
        map.put("num",sum);
        return map;
    }


    //将上传文件信息存储到数据库
    public static int saveFile(MultipartFile file, String filePath, int userId){
        MyFile myfile = new MyFile();
        myfile.setCode("0");
        myfile.setCreateTime(new Date());
        myfile.setFileName(file.getOriginalFilename());
//        myfile.setFileProperty();
        myfile.setFileType(file.getName());
        myfile.setPath(filePath);
        myfile.setShareNum(0);
        myfile.setSize((int) file.getSize());
        myfile.setUserId(userId);
        return myFileService.uploadFile(myfile);

    }


}
