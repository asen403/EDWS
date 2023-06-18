package com.whs.edws.controller;


import com.whs.edws.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/con")
public class HelloController {

    @Resource
    private User user;


    @GetMapping("/hello")
    public String hello(){
       return  "Hello SpringBoot!" + user;
    }


    @GetMapping("/whs")
    public String whs(){
        return "hello whs!";
    }
}
