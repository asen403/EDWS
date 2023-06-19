package com.whs.edws.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whs.edws.entity.District;
import com.whs.edws.mapper.DistrictMapper;
import com.whs.edws.service.DistrictService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictMapper districtMapper;

    public DistrictServiceImpl(DistrictMapper districtMapper) {
        this.districtMapper = districtMapper;
    }

    @Override
    public int insertDistrict(District district) {
        return districtMapper.insertDistrict(district);
    }

    @Override
    public District selectById(int id) {
        return districtMapper.selectById(id);
    }

    @Override
    public PageInfo<District> selectAll(int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<District> districts = districtMapper.selectAll();
        return new PageInfo<>(districts);
    }
}
