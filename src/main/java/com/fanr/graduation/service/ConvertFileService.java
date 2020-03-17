package com.fanr.graduation.service;

import com.fanr.graduation.entity.ConvertFile;
import java.util.List;
import java.util.Map;


/**
 * 转换后文件表(ConvertFile)表服务接口
 *
 * @author makejava
 * @since 2020-03-16 14:30:51
 */
public interface ConvertFileService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ConvertFile queryByFileId(int id);

    /**
     * 查询所有
     * @param params 查询参数（包括分页<start ,length>）
     * @return 返回对象集合
     */
//    Page<ConvertFile> queryAllByLimit(Map<String, Object> params);

    /**
     * 新增数据
     *
     * @param convertFile 实例对象
     * @return 实例对象
     */
    ConvertFile insert(ConvertFile convertFile);

    /**
     * 修改数据
     *
     * @param convertFile 实例对象
     * @return 实例对象
     */
    ConvertFile update(ConvertFile convertFile);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteByFileId(Integer id);

}