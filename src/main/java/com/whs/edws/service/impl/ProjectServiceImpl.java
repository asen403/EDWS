package com.whs.edws.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whs.edws.dao.ProjectDao;
import com.whs.edws.entity.Project;
import com.whs.edws.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectDao projectDao;

    public ProjectServiceImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public PageInfo<Project> queryForPage(int pageIndex, int pageSize, String search) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Project> projects = projectDao.queryAllByName(search);
        return new PageInfo<>(projects);
    }

    @Override
    public boolean saveProject(Project project) {
        return projectDao.insert(project) > 0;
    }

    @Override
    public Project queryById(int id) {
        return projectDao.queryById(id);
    }

    @Override
    public boolean update(Project project) {
        return projectDao.update(project) > 0;
    }
}
