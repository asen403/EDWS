package com.whs.edws.dao;

import com.whs.edws.entity.ProjectPhysio;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 项目理疗师关联表(ProjectPhysio)表数据库访问层
 *
 * @author edws
 * @since 2023-06-28 19:14:20
 */
@Mapper
public interface ProjectPhysioDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProjectPhysio queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param projectPhysio 查询条件
     * @param pageable      分页对象
     * @return 对象列表
     */
    List<ProjectPhysio> queryAllByLimit(ProjectPhysio projectPhysio, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param projectPhysio 查询条件
     * @return 总行数
     */
    long count(ProjectPhysio projectPhysio);

    /**
     * 新增数据
     *
     * @param projectPhysio 实例对象
     * @return 影响行数
     */
    int insert(ProjectPhysio projectPhysio);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ProjectPhysio> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ProjectPhysio> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ProjectPhysio> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ProjectPhysio> entities);

    /**
     * 修改数据
     *
     * @param projectPhysio 实例对象
     * @return 影响行数
     */
    int update(ProjectPhysio projectPhysio);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

