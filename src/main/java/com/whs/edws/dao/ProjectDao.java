package com.whs.edws.dao;

import com.whs.edws.dto.ProjectDto;
import com.whs.edws.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 项目表(Project)表数据库访问层
 *
 * @author edws
 * @since 2023-06-27 20:11:51
 */
@Mapper
public interface ProjectDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Project queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param project  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Project> queryAllByLimit(Project project, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param project 查询条件
     * @return 总行数
     */
    long count(Project project);

    /**
     * 新增数据
     *
     * @param project 实例对象
     * @return 影响行数
     */
    int insert(Project project);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Project> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Project> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Project> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Project> entities);

    /**
     * 修改数据
     *
     * @param project 实例对象
     * @return 影响行数
     */
    int update(Project project);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过名字查询所有名字
     *
     * @param search 搜索
     */
    List<Project> queryAllByName(String search);

    /**
     * 选择id和名称
     *
     * @return {@link List}<{@link ProjectDto}>
     */
    List<ProjectDto> selectIdAndName();
}

