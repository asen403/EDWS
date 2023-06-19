package com.whs.edws.controller;

import com.whs.edws.common.ApiResponse;
import com.whs.edws.entity.District;
import com.whs.edws.service.DistrictService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/district")
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @PostMapping("/save")
    public ApiResponse<Boolean> save(@RequestBody District district){
        int result = districtService.insertDistrict(district);
        if(result > 0){
            return ApiResponse.success(true);
        }
        return ApiResponse.fail(false);
    }
}
