package com.whs.edws.controller;

import com.whs.edws.common.ApiResponse;
import com.whs.edws.dto.UserLoginDto;
import com.whs.edws.entity.User;
import com.whs.edws.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody User user){
        if(userService.register(user)){
            return ApiResponse.success("注册成功");
        }
        return ApiResponse.fail("注册失败");
    }

    @PostMapping("/login")
    public ApiResponse<User> login(@RequestBody UserLoginDto userLoginDto, HttpServletRequest request){
        User user = userService.login(userLoginDto, request);
        if(user != null){
            return ApiResponse.success(user);
        }
        return ApiResponse.fail();
    }

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.captcha(request, response);
    }
}
