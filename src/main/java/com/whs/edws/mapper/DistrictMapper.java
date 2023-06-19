package com.whs.edws.mapper;

import com.whs.edws.entity.District;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DistrictMapper {

    int insertDistrict(District district);

    District selectById(int id);

    List<District> selectAll();
}
