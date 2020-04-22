package com.fanr.graduation.mapper;

import com.fanr.graduation.entity.Approval;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;


/**
 * 审批信息表(Approval)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-22 09:49:15
 */
@Mapper
public interface ApprovalMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Approval queryById(Integer id);
    
     /**
     * 查询所有数据天条数
     *
     * @param params 查询参数
     * @return 返回数据条数
     */
    Integer count();

    /**
     * 查询所有
     *
     * @param params 查询参数（包括分页<start ,length>）
     * @return 返回对象集合
     */
    List<Approval> queryAllByLimit(Map<String, Object> params);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param approval 实例对象
     * @return 对象列表
     */
    List<Approval> queryAll();

    /**
     * 新增数据
     *
     * @param approval 实例对象
     * @return 影响行数
     */
    int insert(Approval approval);

    /**
     * 修改数据
     *
     * @param approval 实例对象
     * @return 影响行数
     */
    int update(Approval approval);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}