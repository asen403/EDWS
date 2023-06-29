package com.whs.edws.service;

import com.github.pagehelper.PageInfo;
import com.whs.edws.entity.Physio;

public interface PhysioService {

    PageInfo<Physio> queryByPage(int pageIndex, int pageSize);

}
