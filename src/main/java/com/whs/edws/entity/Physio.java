package com.whs.edws.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 理疗师表(Physio)实体类
 *
 * @author edws
 * @since 2023-06-27 20:11:49
 */
public class Physio implements Serializable {
    private static final long serialVersionUID = -12345160421203156L;
    /**
     * 主键自增
     */
    private Integer id;
    /**
     * 理疗师名称
     */
    private String name;
    /**
     * 理疗师头像
     */
    private String avatar;
    /**
     * 收藏数
     */
    private String collects;
    /**
     * 点赞数
     */
    private Integer likes;
    /**
     * 消费次数
     */
    private Integer nums;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCollects() {
        return collects;
    }

    public void setCollects(String collects) {
        this.collects = collects;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}

