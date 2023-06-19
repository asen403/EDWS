package com.whs.edws.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Air {

    private Integer id;

    private Integer districtId;

    private Date monitorTime;

    private Integer pm10;

    private Integer pm25;

    private String monitoringStation;

    private Date lastModifyTime;
}
