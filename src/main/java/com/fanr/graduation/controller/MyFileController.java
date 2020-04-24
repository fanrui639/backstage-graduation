package com.fanr.graduation.controller;

import com.fanr.graduation.common.Result;
import com.fanr.graduation.common.ResultUtil;
import com.fanr.graduation.common.UpAndDownFile;
import com.fanr.graduation.entity.Approval;
import com.fanr.graduation.entity.ConvertFile;
import com.fanr.graduation.entity.User;
import com.fanr.graduation.service.ApprovalService;
import com.fanr.graduation.service.ConvertFileService;
import com.fanr.graduation.service.MyFileService;
import com.fanr.graduation.service.UserService;
import org.jodconverter.office.OfficeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.fanr.graduation.entity.MyFile;

import static com.fanr.graduation.common.Office2PDF.office2pdf;

@RestController
@RequestMapping("/api/file")
public class MyFileController {

    @Autowired
    private MyFileService MyfileService;

    @Autowired
    private ConvertFileService convertFileService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApprovalService approvalService;

    private final static String path = "G:/file/";
//    private final static String path = "/home/data/";


    //上传单个文件
    @PostMapping("/uploadFile")
    public Result uploadFile(MultipartFile file,HttpServletRequest request) {

        if(file.getSize() == 0){
            return ResultUtil.error(500,"请不要上传空文件");
        }

        MyFile myFile = new MyFile();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        //判断是否登陆
        if(user == null){
            return ResultUtil.error(500,"请登录后操作");
        }
        //判断是否有剩余空间
        int left = (user.getVolume() * 1024 - user.getUsed());
        int fileSize = (int) (file.getSize()/1024);
        if(left < fileSize){
            return ResultUtil.error(500,"没有剩余空间了！请清理使用空间");
        }
        String filePath = path + user.getUsername();
        int userId = user.getId();
        Map upload = null;
        try {
            upload = UpAndDownFile.upload(filePath,file,userId);

            //从数据库中删除同名记录
            MyFile fileNum= this.MyfileService.getFileID(file.getOriginalFilename(),userId);
            if(fileNum != null){
                int delById = this.MyfileService.deleteById(fileNum.getId());
            }

//            System.out.println("file.getContentType() = " + file.getContentType());

            myFile.setCode("0");
            myFile.setCreateTime(new Date());
            myFile.setFileName(file.getOriginalFilename());
//          myfile.setFileProperty();
            myFile.setFileType(file.getContentType());
            myFile.setPath(filePath);
            myFile.setShareNum(0);
            myFile.setSize((int) file.getSize()/1024);
            myFile.setUserId(userId);
            int result = this.MyfileService.uploadFile(myFile);
            if (result > 0){
                return ResultUtil.success();
            }
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

        //判断是否登陆
        if(user == null){
            return ResultUtil.error(500,"请登录后操作");
        }

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
    @GetMapping("/downloadFile/{fileId}")
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
        File file = new File(fileSaveRootPath + "/" + fileName);
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
        FileInputStream in  = new FileInputStream(fileSaveRootPath + "/" + fileName);

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
    @GetMapping("/downFiles/{fileList}")
    public Result downloadFiles(HttpServletResponse response,@PathVariable String fileList) throws IOException{

        return ResultUtil.success();

    }

    //显示用户所有文件
    @GetMapping("/getFileList")
    public Result queryFileList(HttpServletRequest request,Integer page){

        if (page == null){
            page = 0;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        //判断是否登陆
        if(user == null){
            return ResultUtil.error(500,"请登录后操作");
        }

        int id = user.getId();
        int total = this.MyfileService.getFileNum(id);
        List<MyFile> list = this.MyfileService.queryFileList(id, page);
        this.userService.updateFile(id);

        return ResultUtil.success(total,list);
    }

    //显示共享文件
    @GetMapping("/getShare")
    public Result getShare(Integer page){

        if (page == null){
            page = 0;
        }
        int total = this.MyfileService.getTotal();
        List<MyFile> list = this.MyfileService.getShare(page);

        return ResultUtil.success(total,list);

    }

    //根据搜索内容显示共享文件
    @GetMapping("/getShareBySearch")
    public Result getShareBySearch(String searchContext){
        List<MyFile> list = this.MyfileService.getShareBySearch(searchContext);

        return ResultUtil.success(list);
    }

    //删除文件
    @GetMapping("/deleteFile")
    public Result deleteFile(Integer id){
        if(id != null) {
            MyFile myFile = this.MyfileService.getFileById(id);   //查找文件
            ConvertFile convertFile = this.convertFileService.queryByFileId(id);    //查找是否被转换过

            int result = this.MyfileService.deleteFile(id);

            if (result > 0) {
                String filepath = myFile.getPath() + "/" + myFile.getFileName();

                File targetFile = new File(filepath);
                delFile(targetFile);
                if(convertFile.getId() != null){
                    delFile(new File(convertFile.getPath() + convertFile.getFilename()));
                    this.convertFileService.deleteByFileId(id);
                }
            }

            return ResultUtil.success();
        }else{
            return ResultUtil.error(500,"请检查您的传值");
        }

    }

    //分享文件
    @GetMapping("/shareFile")
    public Result shareFile(Integer id,String shareCode,HttpSession session){

        MyFile myFile = this.MyfileService.getFileById(id);
        User user = (User)session.getAttribute("user");

        if(shareCode.equals("") || shareCode.equals(null)){   //如果没有分享码，那通过审批才能被看到
            Approval approval = new Approval();
            approval.setUser(user.getUsername());
            approval.setStatus(1);
            approval.setEvent(myFile.getFileName());
            approval.setTime(new Date());
            approval.setType(2);
            this.approvalService.insert(approval);
            return ResultUtil.error(403,"等待审批！！！");
        }else{
            int result = this.MyfileService.shareFile(id,shareCode);
            return ResultUtil.success();
        }
    }

    //取消分享文件
    @GetMapping("/unShare")
    public Result unShare(Integer id){

        int result = this.MyfileService.unShare(id);

        return ResultUtil.success();
    }

    //下载分享文件确认分享码
    @GetMapping("/downShareFile")
    public Result downShareFile(Integer id,String code){

        int result = this.MyfileService.downShareFile(id,code);

        if(result > 0) {
            return ResultUtil.success();
        }else{
            return ResultUtil.error(500,"分享码错误！！！");
        }
    }

    //预览文件
    @GetMapping("/seeFile")
    public String seeFile(Integer id, HttpServletResponse response) throws IOException {

        String outFile = "";

        ConvertFile convertfile = this.convertFileService.queryByFileId(id);

        if(convertfile.getId() == null){
            MyFile file = this.MyfileService.getFileById(id);

            String inFile = file.getPath() + "/" + file.getFileName();
            String convertpath = path + "convert/";
            String convertname = file.getFileName().substring(0,file.getFileName().indexOf(".") + 1) + "pdf";
            outFile =  convertpath + convertname;

            try {
                Map map = office2pdf(inFile,outFile);    //将文件转换为PDF格式
                boolean stat = (boolean) map.get("stat");
                if (stat) {
                    //将转换后的文件存进数据库
                    ConvertFile convert = new ConvertFile();
                    convert.setFilename(convertname);
                    convert.setPath(convertpath);
                    File file1 = new File(convertpath + convertname);
                    convert.setFilesize(Double.valueOf(file1.length()));
                    convert.setFileId(id);
                    this.convertFileService.insert(convert);
                }
            } catch (OfficeException e) {
                e.printStackTrace();
                return null;
            }
        }else{
            outFile = convertfile.getPath() + convertfile.getFilename();
        }

        File convertFile = new File(outFile);//convertrealpath为你转换好后的文件的绝对路径

        BufferedInputStream br = new BufferedInputStream(new FileInputStream(convertFile));
        byte[] buf = new byte[1024];
        int len = 0;
        response.reset(); // 非常重要
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=" + URLEncoder.encode(convertFile.getName(), "UTF-8"));

        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
        return null;
    }

    //测试播放视频
    @GetMapping("/video")
    public void getVideo(Integer id,HttpServletResponse response){
        MyFile MyFile = this.MyfileService.getFileById(id);
        String filePath = MyFile.getPath() + "/" + MyFile.getFileName();

        File file = new File(filePath);
        ServletOutputStream out = null;
        try{
            FileInputStream inStream = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length = 0;
            BufferedInputStream bis = new BufferedInputStream(inStream);
            out = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(out);
            while((length=bis.read(b)) != -1){
                bos.write(b,0,b.length);
            }

        }catch(Exception e){
            //TOOD Auto-generated catch block
            e.printStackTrace();
        }
    }


    //删除本地文件
    public static void delFile(File f)
    {

        //如果是文件夹，递归查找
        if(f.isDirectory()) {
            delFile(f);
        }
        else if(f.isFile())
        {
            //是文件的话，把文件名放到一个字符串中
            String filename=f.getName();
            //如果是“class”后缀文件，返回一个boolean型的值
            if(filename.endsWith("class"))
            {
//                System.out.println("成功删除：："+f.getName());
                //file.delete();
            }else{
//                System.out.println("开始删除");
                f.delete();
            }
        }
    }

}
