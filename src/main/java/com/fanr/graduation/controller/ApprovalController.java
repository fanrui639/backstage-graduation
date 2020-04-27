package com.fanr.graduation.controller;

import com.fanr.graduation.common.CusAccessObjectUtil;
import com.fanr.graduation.common.Result;
import com.fanr.graduation.common.ResultUtil;
import com.fanr.graduation.entity.Approval;
import com.fanr.graduation.entity.MyFile;
import com.fanr.graduation.entity.Operation;
import com.fanr.graduation.entity.User;
import com.fanr.graduation.service.ApprovalService;
import com.fanr.graduation.service.MyFileService;
import com.fanr.graduation.service.OperationService;
import com.fanr.graduation.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

import static com.fanr.graduation.common.Email.sendEmail;

/**
 * 审批信息表(Approval)表控制层
 *
 * @author makejava
 * @since 2020-04-22 09:49:17
 */
@Api(tags = "审批信息表控制器")
@RestController
@RequestMapping("/api/approval")
public class ApprovalController {
    /**
     * 服务对象
     */
    @Resource
    private ApprovalService approvalService;

    @Resource
    private UserService userService;

    @Resource
    private MyFileService myFileService;

    @Resource
    private OperationService operationService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "根据id查询单条数据", notes = "根据id查询单条数据", response = Result.class)
    @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "Integer")
    @GetMapping("/getById")
    public Result getById(@RequestParam Integer id) {
        Approval approval=this.approvalService.queryById(id);
         return ResultUtil.success(approval);
    }
    
     /**
     * 添加数据
     * @param approval  插入的数据实体
     * @return
     */
    @ApiOperation(value = "新增数据", notes = "新增数据", response = Result.class)
    @ApiImplicitParam(name = "approval", value = " Approval实体类", required = true, dataType = "Approval")
    @PostMapping ("/add")
    public Result add(@RequestBody  Approval approval){
        Approval insert = this.approvalService.insert(approval);
        return ResultUtil.success();
    }
    /**
     * 查询所有
     * @return 返回数据集合
     */
    @GetMapping("/queryAll")
    public Result queryAll(){
        List<Approval> list = this.approvalService.queryAll();
        return ResultUtil.success(list);
    }

    /**
     * 根据id删除数据 并发送邮件
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id 删除数据", notes = "根据id 删除数据", response = Result.class)
    @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "Integer")
    @GetMapping("/remove")
    public Result remove(Integer id, String user, HttpSession session, HttpServletRequest request){
        boolean b = this.approvalService.deleteById(id);
        if(b){
            User apUser = this.userService.getUserByName(user);    //获取申请人信息
            User opUser = (User)session.getAttribute("user");    //获取操作人信息

            String ip = CusAccessObjectUtil.getIp2(request);
            Operation operation = new Operation();
            operation.setOperator(opUser.getUsername());
            operation.setEvent("删除用户 " + user + " 的申请!");
            operation.setTime(new Date());
            operation.setOperrationIp(ip);
            operation.setType("approval");
            try{
                sendEmail("申请进度通知:","您的申请已被驳回,管理员：" + opUser.getUsername(),apUser.getEmail());
                operation.setRemarks("已发送邮件通知");
            }catch (Exception e){
                e.printStackTrace();
                operation.setRemarks("通知邮件发送失败");
            }
            Operation insert = this.operationService.insert(operation);
        }else{
            return ResultUtil.error(500,"操作失败");
        }
        return ResultUtil.success();
    }


    /**
     * 修改数据
     * @param approval 修改实体类
     * @return
     */
    @ApiOperation(value = "修改数据", notes = "修改数据", response = Result.class)
    @ApiImplicitParam(name = "approval", value = " Approval实体类", required = true, dataType = "Approval")
    @PostMapping("/modify")
    public Result modify(@RequestBody  Approval approval){
        Approval update = this.approvalService.update(approval);
        return ResultUtil.success();
    }

    /**
     * 查询条数
     */
    @GetMapping("/getNum")
    public Result getNum(){
        int num = this.approvalService.getNum();
        return ResultUtil.success(num);
    }


    /**
     * 删除用户申请
     */
    @GetMapping("/deleteUser")
    public Result deleteUser(Integer id,String user, HttpServletRequest request){
        User opUser = this.userService.getUserByName(user);
        int num = this.userService.deleteUser(String.valueOf(opUser.getId()));
        if(num > 0){
            Approval approval = new Approval();
            approval.setId(id);
            approval.setStatus(0);
            this.approvalService.update(approval);

            String ip = CusAccessObjectUtil.getIp2(request);
            Operation operation = new Operation();
            operation.setOperator(opUser.getUsername());
            operation.setEvent("注销用户：" + user);
            operation.setTime(new Date());
            operation.setOperrationIp(ip);
            operation.setType("approval");
            Operation insert = this.operationService.insert(operation);

        }
        return ResultUtil.success();
    }

    /**
     * 通过无分享码文件
     */
    @GetMapping("/passFile")
    public Result passFile(Integer id,String user,String file, HttpSession session, HttpServletRequest request){

        User myUser = this.userService.getUserByName(user);
        MyFile opFile = this.myFileService.getFile(myUser.getId(),file);

        User opUser = (User)session.getAttribute("user");    //获取操作人信息

        opFile.setFileProperty(1);
        int num = this.myFileService.shareFile(opFile.getId(),"0");

        if(num > 0){
            Approval approval = new Approval();
            approval.setId(id);
            approval.setStatus(0);
            this.approvalService.update(approval);

            String ip = CusAccessObjectUtil.getIp2(request);
            Operation operation = new Operation();
            operation.setOperator(opUser.getUsername());
            operation.setEvent("通过用户 " + user + " 的文件 " + file + " 无分享码分享");
            operation.setTime(new Date());
            operation.setOperrationIp(ip);
            operation.setType("approval");
            Operation insert = this.operationService.insert(operation);
        }

        return ResultUtil.success();
    }


    /**
     * 查看审批事件详情
     */
    @GetMapping("/showEvent")
    public Result showEvent(HttpServletRequest request){
        System.out.println(CusAccessObjectUtil.getIp2(request));
        String requestUrlIP = request.getServerName();
        String userIpAddr = request.getRemoteAddr();
        System.out.println("***访问的Url中的服务器IP："+requestUrlIP);
        System.out.println("***用户客户端的IP地址："+userIpAddr);
        return ResultUtil.success();
    }

}