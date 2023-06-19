package com.whs.edws.controller;

import com.whs.edws.common.ApiResponse;
import com.whs.edws.entity.District;
import com.whs.edws.service.DistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/district")
@Api(tags = "区域控制器")
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @PostMapping("/save")
    @ApiOperation("保存district")
    @ApiImplicitParam(name = "district", value = "区域对象", required = true)
    public ApiResponse<Boolean> save(@RequestBody District district){
        // Spring自带参数校验
        Assert.hasText(district.getName(), "区域名称不能为空");
        int result = districtService.insertDistrict(district);
        if(result > 0){
            return ApiResponse.success(true);
        }
        return ApiResponse.fail(false);
    }
}
