package com.whs.edws.service;

import com.github.pagehelper.PageInfo;
import com.whs.edws.entity.District;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DistrictServiceTest {

    /*private final DistrictService districtService;

    public DistrictServiceTest(DistrictService districtService) {
        this.districtService = districtService;
    }*/

    @Resource
    DistrictService districtService;

    @Test
    void insertDistrict() {
        District district = new District();
        district.setName("高新区");
        boolean b = districtService.insertDistrict(district);
        System.out.println(b);
    }

    @Test
    void selectById() {
        District district = districtService.selectById(1);
        System.out.println(district);
    }

    @Test
    void selectAll() {
        PageInfo<District> districtPageInfo = districtService.selectAll(1, 3);
        System.out.println(districtPageInfo);
    }
}