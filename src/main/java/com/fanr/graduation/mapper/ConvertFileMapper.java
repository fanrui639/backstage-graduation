package com.fanr.graduation.mapper;

import com.fanr.graduation.entity.ConvertFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 转换后文件表(ConvertFile)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-16 14:30:49
 */
@Mapper
@Repository
public interface ConvertFileMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ConvertFile queryByFileId(Integer id);

    @Select("select count(*) from graduation.convert_file where file_id = #{fileId}")
    int queryById(int id);

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
    List<ConvertFile> queryAllByLimit(Map<String, Object> params);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param convertFile 实例对象
     * @return 对象列表
     */
    List<ConvertFile> queryAll(ConvertFile convertFile);

    /**
     * 新增数据
     *
     * @param convertFile 实例对象
     * @return 影响行数
     */
    int insert(ConvertFile convertFile);

    /**
     * 修改数据
     *
     * @param convertFile 实例对象
     * @return 影响行数
     */
    int update(ConvertFile convertFile);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteByFileId(Integer id);

}