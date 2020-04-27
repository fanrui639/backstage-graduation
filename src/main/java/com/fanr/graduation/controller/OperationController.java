package com.fanr.graduation.controller;

import com.fanr.graduation.common.Result;
import com.fanr.graduation.common.ResultUtil;
import com.fanr.graduation.entity.Operation;
import com.fanr.graduation.service.OperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 系统操作日志表(Operation)表控制层
 *
 * @author makejava
 * @since 2020-04-26 16:16:28
 */
@Api(tags = "系统操作日志表控制器")
@RestController
@RequestMapping("/api/operation")
public class OperationController {
    /**
     * 服务对象
     */
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
        Operation operation=this.operationService.queryById(id);
         return ResultUtil.success(operation);
    }
    
     /**
     * 添加数据
     * @param operation  插入的数据实体
     * @return
     */
    @ApiOperation(value = "新增数据", notes = "新增数据", response = Result.class)
    @ApiImplicitParam(name = "operation", value = " Operation实体类", required = true, dataType = "Operation")
    @PostMapping ("/add")
    public Result add(@RequestBody  Operation operation){
        System.out.println("operation = " + operation);
        Operation insert = this.operationService.insert(operation);
        return ResultUtil.success();
    }



    /**
     * 查询所有
     * @return 返回数据集合
     */
    @ApiOperation(value = "分页查询所有数据", notes = "分页查询所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "每页显示条数", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/queryAll")
    public Result queryAll(Integer page){
        if (page == null){
            page = 0;
        }
        long total = this.operationService.count();
        List<Operation> list = this.operationService.queryAllByLimit(page);
        return ResultUtil.success(total,list);
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
        boolean b = this.operationService.deleteById(id);

        return ResultUtil.success();
    }


    /**
     * 修改数据
     * @param operation 修改实体类
     * @return
     */
    @ApiOperation(value = "修改数据", notes = "修改数据", response = Result.class)
    @ApiImplicitParam(name = "operation", value = " Operation实体类", required = true, dataType = "Operation")
    @PostMapping("/modify")
    public Result modify(@RequestBody  Operation operation){
        Operation update = this.operationService.update(operation);
        return ResultUtil.success();
    }

    /**
     * 条件查询
     */
    @GetMapping("/search")
    public Result search(String operator,String event,String operrationIp,String beginTime,String endTime,Integer page){

        Map map = new HashMap();
        map.put("operator",operator);
        map.put("event",event);
        map.put("operrationIp",operrationIp);
        map.put("beginTime",beginTime + " 00:00:00");
        map.put("endTime",endTime + " 23:59:59");
        map.put("page",page);

        long total = this.operationService.countSearch(map);

        List<Operation> list = this.operationService.search(map);

        return ResultUtil.success(total,list);
    }

}