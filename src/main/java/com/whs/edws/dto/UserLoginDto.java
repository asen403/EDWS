package com.whs.edws.dto;


import lombok.Data;

@Data
public class UserLoginDto {

    private String username;

    private String password;

    private String captcha;
}
