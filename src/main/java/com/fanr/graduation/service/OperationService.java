package com.fanr.graduation.service;

import com.fanr.graduation.entity.Operation;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


/**
 * 系统操作日志表(Operation)表服务接口
 *
 * @author makejava
 * @since 2020-04-26 16:16:28
 */
public interface OperationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Operation queryById(Integer id);

    /**
     * 查询所有
     * @param params 查询参数（包括分页<start ,length>）
     * @return 返回对象集合
     */
    List<Operation> queryAllByLimit(int page);

    /**
     * 查询所有条数
     */
    long count();

    /**
     * 新增数据
     *
     * @param operation 实例对象
     * @return 实例对象
     */
    Operation insert(Operation operation);

    /**
     * 修改数据
     *
     * @param operation 实例对象
     * @return 实例对象
     */
    Operation update(Operation operation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 条件查询
     */
    List<Operation> search(Map map);

    /**
     * 条件查询总条数
     */
    long countSearch(Map map);

}