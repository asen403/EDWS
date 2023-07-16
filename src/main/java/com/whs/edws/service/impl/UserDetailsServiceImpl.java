package com.whs.edws.service.impl;

import com.whs.edws.dao.UserDao;
import com.whs.edws.dto.LoginUserDto;
import com.whs.edws.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.queryByName(username);
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误!");
        }
        List<String> list = userDao.queryUserMenu(user.getId());
        return new LoginUserDto(user, list);
    }
}
