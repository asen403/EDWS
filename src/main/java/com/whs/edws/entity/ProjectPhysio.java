package com.whs.edws.entity;

import java.io.Serializable;

/**
 * 项目理疗师关联表(ProjectPhysio)实体类
 *
 * @author edws
 * @since 2023-06-28 19:14:20
 */
public class ProjectPhysio implements Serializable {
    private static final long serialVersionUID = -28893851467632865L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 理疗师id
     */
    private Integer physioId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getPhysioId() {
        return physioId;
    }

    public void setPhysioId(Integer physioId) {
        this.physioId = physioId;
    }

}

