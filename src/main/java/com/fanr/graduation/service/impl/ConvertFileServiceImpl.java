package com.fanr.graduation.service.impl;

import com.fanr.graduation.entity.ConvertFile;
import com.fanr.graduation.mapper.ConvertFileMapper;
import com.fanr.graduation.service.ConvertFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 转换后文件表(ConvertFile)表服务实现类
 *
 * @author makejava
 * @since 2020-03-16 14:30:51
 */
@Service("convertFileService")
public class ConvertFileServiceImpl implements ConvertFileService {
    @Resource
    private ConvertFileMapper convertFileDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ConvertFile queryByFileId(int id) {
        int result = this.convertFileDao.queryById(id);
        if(result > 0){
            return this.convertFileDao.queryByFileId(id);
        }else{
            return new ConvertFile();
        }
    }

    /**
     * 查询所有
     * @param params 查询参数（包括分页<start ,length>）
     * @return 返回对象集合
     */
//    @Override
//    public Page<ConvertFile> queryAllByLimit(Map<String, Object> params) {
//
//      long total = this.convertFileDao.count(params);
//        List<ConvertFile> list = Collections.emptyList();
//        if (total > 0) {
//           PageUtil.pageUtil(params);
//           list=this.convertFileDao.queryAllByLimit(params);
//        }
//        return new Page<>(total,list);
//    }

    /**
     * 新增数据
     *
     * @param convertFile 实例对象
     * @return 实例对象
     */
    @Override
    public ConvertFile insert(ConvertFile convertFile) {

        this.convertFileDao.insert(convertFile);
        return convertFile;
    }

    /**
     * 修改数据
     *
     * @param convertFile 实例对象
     * @return 实例对象
     */
    @Override
    public ConvertFile update(ConvertFile convertFile) {
        this.convertFileDao.update(convertFile);
        return this.queryByFileId(convertFile.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteByFileId(Integer id) {
        return this.convertFileDao.deleteByFileId(id) > 0;
    }
}