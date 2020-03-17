package com.fanr.graduation.controller;

import com.fanr.graduation.common.Result;
import com.fanr.graduation.common.ResultUtil;
import com.fanr.graduation.entity.ConvertFile;
import com.fanr.graduation.service.ConvertFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
/**
 * 转换后文件表(ConvertFile)表控制层
 *
 * @author makejava
 * @since 2020-03-16 14:30:51
 */
@Api(tags = "转换后文件表控制器")
@RestController
@RequestMapping("/convertFile")
public class ConvertFileController {
    /**
     * 服务对象
     */
    @Resource
    private ConvertFileService convertFileService;

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
        ConvertFile convertFile=this.convertFileService.queryByFileId(id);
         return ResultUtil.success(convertFile);
    }
    
     /**
     * 添加数据
     * @param convertFile  插入的数据实体
     * @return
     */
    @ApiOperation(value = "新增数据", notes = "新增数据", response = Result.class)
    @ApiImplicitParam(name = "convertFile", value = " ConvertFile实体类", required = true, dataType = "ConvertFile")
    @PostMapping ("/add")
    public Result add(@RequestBody  ConvertFile convertFile){
        ConvertFile insert = this.convertFileService.insert(convertFile);
        return ResultUtil.success();
    }


    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id 删除数据", notes = "根据id 删除数据", response = Result.class)
    @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "Integer")
    @GetMapping("/remove")
    public Result remove(@RequestParam Integer id){
        boolean b = this.convertFileService.deleteByFileId(id);

        return ResultUtil.success();
    }
    /**
     * 修改数据
     * @param convertFile 修改实体类
     * @return
     */
    @ApiOperation(value = "修改数据", notes = "修改数据", response = Result.class)
    @ApiImplicitParam(name = "convertFile", value = " ConvertFile实体类", required = true, dataType = "ConvertFile")
    @PostMapping("/modify")
    public Result modify(@RequestBody  ConvertFile convertFile){
        ConvertFile update = this.convertFileService.update(convertFile);
        return ResultUtil.success();
    }

}