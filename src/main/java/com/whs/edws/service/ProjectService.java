package com.whs.edws.service;

import com.github.pagehelper.PageInfo;
import com.whs.edws.entity.Project;

public interface ProjectService {

    PageInfo<Project> queryForPage(int pageIndex, int pageSize, String search);

    boolean saveProject(Project project);

    Project queryById(int id);

    boolean update(Project project);
}
