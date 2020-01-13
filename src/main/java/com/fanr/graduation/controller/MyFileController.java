package com.fanr.graduation.controller;

import com.fanr.graduation.common.Result;
import com.fanr.graduation.common.ResultUtil;
import com.fanr.graduation.common.UpAndDownFile;
import com.fanr.graduation.entity.User;
import com.fanr.graduation.service.MyFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import com.fanr.graduation.entity.MyFile;

@RestController
@RequestMapping("/api/file")
public class MyFileController {

    @Autowired
    private MyFileService MyfileService;

    private final static String path = "G:\\file\\";

    //上传单个文件
    @PostMapping("/uploadFile")
    public Result uploadFile(MultipartFile file,HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String filePath = path + user.getUsername();
        int userId = user.getId();
        Map upload = null;
        try {
            upload = UpAndDownFile.upload(filePath,file,userId);
            System.out.println("upload = " + upload);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return ResultUtil.success(upload);
    }

    //上传多个文件
    @PostMapping("/uploadFiles")
    public Result uploadFiles(HttpServletRequest request,MultipartFile[] files) {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String filePath = path + user.getUsername();
        int userId = user.getId();

        Map uploads = null;

        try {
            uploads = UpAndDownFile.uploads(filePath,files,userId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResultUtil.success(uploads);
    }

    //下载单个文件
    @GetMapping("downFile/{fileId}")
    public Result downloadFile(HttpServletRequest request, HttpServletResponse response,@PathVariable String fileId) throws IOException {
        //得到要下载的文件名
//        String fileName = URLDecoder.decode(request.getParameter("diarycontent"), "utf-8");
//        String fileName = filename;
        MyFile myFile = this.MyfileService.queryById(fileId);

        String fileName = myFile.getFileName();

        String fileSaveRootPath = myFile.getPath();

        //fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        //上传的文件都是保存在G:\file\目录下的子目录当中
//        String fileSaveRootPath = "G:\\file\\";
//        System.out.println(URLDecoder.decode(request.getParameter("diarycontent"), "utf-8"));
        //通过文件名找出文件的所在目录
        //String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
        //String path = fileSaveRootPath;
        //得到要下载的文件
        File file = new File(fileSaveRootPath + "\\" + fileName);
        //如果文件不存在
        if (!file.exists()) {

            return ResultUtil.error(404,"您要下载的资源已被删除！！");
//            request.setAttribute("message", "您要下载的资源已被删除！！");
        }
        //处理文件名
//        String realname = fileName.substring(fileName.indexOf("_") + 1);
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        //读取要下载的文件，保存到文件输入流
        FileInputStream in  = new FileInputStream(fileSaveRootPath + "\\" + fileName);

        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while ((len = in.read(buffer)) > 0) {
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();

        return ResultUtil.success();
    }

    //下载多个文件
    @GetMapping("downFiles/{fileList}")
    public Result downloadFiles(HttpServletResponse response,@PathVariable String fileList) throws IOException{

        return ResultUtil.success();

    }


    //显示用户所有文件
    @GetMapping("/getFileList")
    public Result queryFileList(HttpServletRequest request,String page){

        if (page.equals("") || page.equals(null)){
            page = "0";
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int id = user.getId();
        List<MyFile> list = this.MyfileService.queryFileList(id, page);

        return ResultUtil.success(list);
    }


}
