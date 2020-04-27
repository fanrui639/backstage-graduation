package com.fanr.graduation.service.impl;

import com.fanr.graduation.entity.Operation;
import com.fanr.graduation.mapper.OperationDao;
import com.fanr.graduation.service.OperationService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * 系统操作日志表(Operation)表服务实现类
 *
 * @author makejava
 * @since 2020-04-26 16:16:28
 */
@Service("operationService")
public class OperationServiceImpl implements OperationService {
    @Resource
    private OperationDao operationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Operation queryById(Integer id) {
        return this.operationDao.queryById(id);
    }

    /**
     * 查询所有
     * @param params 查询参数（包括分页<start ,length>）
     * @return 返回对象集合
     */
    @Override
    public List<Operation> queryAllByLimit(int page) {
        List<Operation> list = this.operationDao.queryAllByLimit(page);
        return list;
    }

    /**
     * 查询所有条数
     */
    @Override
    public long count(){
        return this.operationDao.count();
    }

    /**
     * 新增数据
     *
     * @param operation 实例对象
     * @return 实例对象
     */
    @Override
    public Operation insert(Operation operation) {
        this.operationDao.insert(operation);
        return operation;
    }

    /**
     * 修改数据
     *
     * @param operation 实例对象
     * @return 实例对象
     */
    @Override
    public Operation update(Operation operation) {
        this.operationDao.update(operation);
        return this.queryById(operation.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.operationDao.deleteById(id) > 0;
    }

    /**
     * 条件查询
     */
    @Override
    public List<Operation> search(Map map){
        return this.operationDao.search(map);
    }

    @Override
    public long countSearch(Map map) {
        return this.operationDao.countSearch(map);
    }

}