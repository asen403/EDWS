package com.whs.edws.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class District implements Serializable {

    private static final long serialVersionUID = 2009968559477642557L;

    private Integer id;

    private String name;
}
