package com.whs.edws.service;

import com.whs.edws.dto.UserLoginDto;
import com.whs.edws.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserService {

     boolean register(User user);

     User login(UserLoginDto userLoginDto, HttpServletRequest request);

     void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException;


}
