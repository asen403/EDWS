package com.whs.edws.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/con")
public class HelloController {

    
    @GetMapping("/hello")
    public String hello(){
       return  "Hello SpringBoot!";
    }
}
