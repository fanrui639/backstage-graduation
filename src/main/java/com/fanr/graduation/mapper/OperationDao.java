package com.fanr.graduation.mapper;

import com.fanr.graduation.entity.Operation;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;


/**
 * 系统操作日志表(Operation)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-26 16:16:26
 */
@Mapper
public interface OperationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Operation queryById(Integer id);
    
     /**
     * 查询所有数据天条数
     *
     * @param params 查询参数
     * @return 返回数据条数
     */
    long count(Map<String, Object> params);

    /**
     * 查询所有
     *
     * @param params 查询参数（包括分页<start ,length>）
     * @return 返回对象集合
     */
    List<Operation> queryAllByLimit(int page);

    /**
     * 查询所有条数
     */
    long count();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param operation 实例对象
     * @return 对象列表
     */
    List<Operation> queryAll(Operation operation);

    /**
     * 新增数据
     *
     * @param operation 实例对象
     * @return 影响行数
     */
    int insert(Operation operation);

    /**
     * 修改数据
     *
     * @param operation 实例对象
     * @return 影响行数
     */
    int update(Operation operation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 条件查询
     */
    List<Operation> search(Map map);

    long countSearch(Map map);
}