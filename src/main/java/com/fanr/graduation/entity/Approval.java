package com.fanr.graduation.entity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

/**
 * 审批信息表(Approval)实体类
 *
 * @author makejava
 * @since 2020-04-22 14:14:25
 */
@Data
@ToString
public class Approval implements Serializable {
    private static final long serialVersionUID = 682388118925222274L;
    
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", dataType = "Integer")    
    private Integer id;
    
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称", dataType = "String")    
    private String user;
    
    /**
     * 审批事项
     */
    @ApiModelProperty(value = "审批事项", dataType = "String")    
    private String event;
    
    /**
     * 审批类型 1-注销用户 2-分享文件
     */
    @ApiModelProperty(value = "审批类型 1-注销用户 2-分享文件", dataType = "Integer")    
    private Integer type;
    
    /**
     * 提交审批时间
     */
    @ApiModelProperty(value = "提交审批时间", dataType = "Date")    
    private Date time;
    
    /**
     * 状态 0-已审批 1-待审批
     */
    @ApiModelProperty(value = "状态 0-已审批 1-待审批", dataType = "Integer")    
    private Integer status;
    



}