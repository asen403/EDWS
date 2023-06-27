package com.whs.edws.service.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.wf.captcha.ArithmeticCaptcha;
import com.whs.edws.dao.UserDao;
import com.whs.edws.dto.UserLoginDto;
import com.whs.edws.entity.User;
import com.whs.edws.service.UserService;
import com.whs.edws.utils.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final RedisUtil redisUtil;


    public UserServiceImpl(UserDao userDao, RedisUtil redisUtil) {
        this.userDao = userDao;
        this.redisUtil = redisUtil;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(User user) {
        // 校验参数
        Assert.hasText(user.getUsername(), "用户姓名不能为空");
        Assert.hasText(user.getPassword(), "用户密码不能为空");
        // 密码加密
        user.setPassword(SecureUtil.sha256(user.getPassword()));
        // 入库
        return userDao.insert(user) > 0;
    }

    @Override
    public User login(UserLoginDto userLoginDto, HttpServletRequest request) {
        // 根据用户名查询用户信息
        User user = userDao.queryByName(userLoginDto.getUsername());
        String clientIP = ServletUtil.getClientIP(request);
        if(!Objects.isNull(user)){
            // 对比密码
            if(SecureUtil.sha256(userLoginDto.getPassword()).equals(user.getPassword())
                    && userLoginDto.getCaptcha().equals(redisUtil.get("captcha:"+clientIP))){
                // 存入缓存
                redisUtil.set(user.getUsername(), user);
                return user;
            }
        }
        return null;
    }

    @Override
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String clientIP = ServletUtil.getClientIP(request);
        ArithmeticCaptcha captcha = new ArithmeticCaptcha();
        redisUtil.set("captcha:"+clientIP, captcha.text(), 300);
        response.setContentType("application/image");
        captcha.out(response.getOutputStream());
    }
}
