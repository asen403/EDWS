package com.whs.edws.service;

import com.github.pagehelper.PageInfo;
import com.whs.edws.entity.District;

public interface DistrictService {


    boolean insertDistrict(District district);

    District selectById(int id);

    PageInfo<District> selectAll(int pageIndex, int pageSize);
}
