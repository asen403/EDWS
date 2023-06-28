package com.whs.edws.controller;


import com.github.pagehelper.PageInfo;
import com.whs.edws.common.ApiResponse;
import com.whs.edws.entity.Project;
import com.whs.edws.service.ProjectService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/list")
    public ApiResponse<PageInfo<Project>> queryByPage(int pageIndex, int pageSize, String search){
        PageInfo<Project> projectPageInfo = projectService.queryForPage(pageIndex, pageSize, search);
        return ApiResponse.success(projectPageInfo);
    }

    @PostMapping("/add")
    public ApiResponse<String> save(@RequestBody Project project){
        if(projectService.saveProject(project)){
            return ApiResponse.success("添加成功");
        }
        return ApiResponse.fail("添加失败");
    }

    @GetMapping("/queryById/{id}")
    public ApiResponse<Project> queryById(@PathVariable("id") int id){
        return ApiResponse.success(projectService.queryById(id));
    }

    @PostMapping("update")
    public ApiResponse<String> update(@RequestBody Project project){
        if(projectService.update(project)){
            return ApiResponse.success("更新成功");
        }
        return ApiResponse.fail("更新失败");
    }

}
