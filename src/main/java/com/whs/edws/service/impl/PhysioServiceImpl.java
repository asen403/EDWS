package com.whs.edws.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whs.edws.dao.PhysioDao;
import com.whs.edws.entity.Physio;
import com.whs.edws.service.PhysioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhysioServiceImpl implements PhysioService {

    private final PhysioDao physioDao;

    public PhysioServiceImpl(PhysioDao physioDao) {
        this.physioDao = physioDao;
    }

    @Override
    public PageInfo<Physio> queryByPage(int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Physio> physios = physioDao.queryAll();
        return new PageInfo<>(physios);
    }
}
