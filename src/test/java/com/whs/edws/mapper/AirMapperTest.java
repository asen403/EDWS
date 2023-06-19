package com.whs.edws.mapper;

import com.whs.edws.entity.Air;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
class AirMapperTest {

    @Resource
    private AirMapper airMapper;

    @Test
    void insert() {
        Air air = new Air();
        air.setDistrictId(1);
        air.setPm10(11);
        air.setPm25(22);
        air.setMonitorTime(new Date());
        air.setLastModifyTime(new Date());
        air.setMonitoringStation("测试insert方法");
        int insert = airMapper.insert(air);
        System.out.println("插入方法返回值为："+ insert);
    }

    @Test
    void delete() {
        System.out.println("delete方法返回值为：" + airMapper.delete(1));
    }

    @Test
    void update() {
        Air air = new Air();
        air.setId(1);
        air.setPm10(10);
        int update = airMapper.update(air);
        System.out.println("update方法返回值为：" + update);
    }

    @Test
    void selectById() {
        Air air = airMapper.selectById(1);
        System.out.println(air);
    }

    @Test
    void selectAll() {
        List<Air> airs = airMapper.selectAll();
        airs.forEach(System.out::println);
    }
}