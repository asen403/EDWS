package com.whs.edws.service;

import com.whs.edws.dto.UserLoginDto;
import com.whs.edws.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface UserService {

     boolean register(User user);

     Map<String, String> login(UserLoginDto userLoginDto, HttpServletRequest request, HttpServletResponse response);

     void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException;


}
