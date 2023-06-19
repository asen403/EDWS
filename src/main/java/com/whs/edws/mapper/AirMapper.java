package com.whs.edws.mapper;

import com.whs.edws.entity.Air;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AirMapper {

    int insert(Air air);

    int delete(Integer id);

    int update(Air air);


    Air selectById(Integer id);

    List<Air> selectAll();
}
