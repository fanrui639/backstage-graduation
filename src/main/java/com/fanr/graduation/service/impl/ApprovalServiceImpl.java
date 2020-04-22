package com.fanr.graduation.service.impl;

import com.fanr.graduation.entity.Approval;
import com.fanr.graduation.mapper.ApprovalMapper;
import com.fanr.graduation.service.ApprovalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 审批信息表(Approval)表服务实现类
 *
 * @author makejava
 * @since 2020-04-22 09:49:17
 */
@Service("approvalService")
public class ApprovalServiceImpl implements ApprovalService {
    @Resource
    private ApprovalMapper approvalMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Approval queryById(Integer id) {
        return this.approvalMapper.queryById(id);
    }

    /**
     * 查询所有
     * @param params 查询参数（包括分页<start ,length>）
     * @return 返回对象集合
     */
    @Override
    public List<Approval> queryAll() {
    

        List<Approval> list = this.approvalMapper.queryAll();

        return list;
    }

    /**
     * 新增数据
     *
     * @param approval 实例对象
     * @return 实例对象
     */
    @Override
    public Approval insert(Approval approval) {
//      if (StringUtils.isEmpty(approval.getId())){
//            approval.setId(UUIDUtils.getUUID());
//        }
        this.approvalMapper.insert(approval);
        return approval;
    }

    /**
     * 修改数据
     *
     * @param approval 实例对象
     * @return 实例对象
     */
    @Override
    public Approval update(Approval approval) {
        this.approvalMapper.update(approval);
        return this.queryById(approval.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.approvalMapper.deleteById(id) > 0;
    }

    @Override
    public Integer getNum(){
        return this.approvalMapper.count();
    }
}