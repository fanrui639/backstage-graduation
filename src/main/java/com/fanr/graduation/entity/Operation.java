package com.fanr.graduation.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

/**
 * 系统操作日志表(Operation)实体类
 *
 * @author makejava
 * @since 2020-04-26 16:16:26
 */
//@Data
//@ToString
public class Operation implements Serializable {
    private static final long serialVersionUID = -35065376727583457L;
    
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", dataType = "Integer")    
    private Integer id;
    
    /**
     * 操作者
     */
    @ApiModelProperty(value = "操作者", dataType = "String")    
    private String operator;
    
    /**
     * 操作事件
     */
    @ApiModelProperty(value = "操作事件", dataType = "String")    
    private String event;
    
    /**
     * 操作事件
     */
    @ApiModelProperty(value = "操作事件", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date time;
    
    /**
     * 操作电脑的ip
     */
    @ApiModelProperty(value = "操作电脑的ip", dataType = "String")    
    private String operrationIp;
    
    /**
     * 操作类型
     */
    @ApiModelProperty(value = "操作类型", dataType = "String")    
    private String type;
    
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", dataType = "String")    
    private String remarks;

    public Operation() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getOperrationIp() {
        return operrationIp;
    }

    public void setOperrationIp(String operrationIp) {
        this.operrationIp = operrationIp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", operator='" + operator + '\'' +
                ", event='" + event + '\'' +
                ", time=" + time +
                ", operrationIp='" + operrationIp + '\'' +
                ", type='" + type + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}