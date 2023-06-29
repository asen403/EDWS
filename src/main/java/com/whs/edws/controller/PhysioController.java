package com.whs.edws.controller;

import com.github.pagehelper.PageInfo;
import com.whs.edws.common.ApiResponse;
import com.whs.edws.entity.Physio;
import com.whs.edws.service.PhysioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/physio")
public class PhysioController {

    private final PhysioService physioService;

    public PhysioController(PhysioService physioService) {
        this.physioService = physioService;
    }

    @PostMapping("/list")
    public ApiResponse<PageInfo<Physio>> queryAll(int pageIndex, int pageSize){
        return ApiResponse.success(physioService.queryByPage(pageIndex, pageSize));
    }
}
