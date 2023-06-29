package com.whs.edws.service;

import com.github.pagehelper.PageInfo;
import com.whs.edws.dto.ProjectDto;
import com.whs.edws.entity.Project;

import java.util.List;

public interface ProjectService {

    PageInfo<Project> queryForPage(int pageIndex, int pageSize, String search);

    boolean saveProject(Project project);

    Project queryById(int id);

    boolean update(Project project);

    List<ProjectDto> selectIdAndName();
}
