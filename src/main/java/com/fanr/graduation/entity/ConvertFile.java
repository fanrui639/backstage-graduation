package com.fanr.graduation.entity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 转换后文件表(ConvertFile)实体类
 *
 * @author makejava
 * @since 2020-03-16 14:30:49
 */
@Data
@ToString
public class ConvertFile implements Serializable {
    private static final long serialVersionUID = 965988874145474483L;
    
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", dataType = "Integer")    
    private Integer id;
    
    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称", dataType = "String")    
    private String filename;
    
    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小", dataType = "Double")    
    private Double filesize;
    
    /**
     * 文件位置
     */
    @ApiModelProperty(value = "文件位置", dataType = "String")    
    private String path;
    
    /**
     * 文件数据表中的id
     */
    @ApiModelProperty(value = "文件数据表中的id", dataType = "Integer")    
    private Integer fileId;
    



}