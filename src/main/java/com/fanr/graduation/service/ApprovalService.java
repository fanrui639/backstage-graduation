package com.fanr.graduation.service;

import com.fanr.graduation.entity.Approval;
import java.util.List;


/**
 * 审批信息表(Approval)表服务接口
 *
 * @author makejava
 * @since 2020-04-22 09:49:17
 */
public interface ApprovalService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Approval queryById(Integer id);

    /**
     * 查询所有
     * @param params 查询参数（包括分页<start ,length>）
     * @return 返回对象集合
     */
    List<Approval> queryAll();

    /**
     * 新增数据
     *
     * @param approval 实例对象
     * @return 实例对象
     */
    Approval insert(Approval approval);

    /**
     * 修改数据
     *
     * @param approval 实例对象
     * @return 实例对象
     */
    Approval update(Approval approval);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 获取总条数
     */
    Integer getNum();

}