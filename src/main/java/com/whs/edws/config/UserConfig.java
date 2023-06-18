package com.whs.edws.config;


import com.whs.edws.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public User user(){
        User user = new User();
        user.setId(1);
        user.setName("workHard");
        user.setAge(18);
        return user;
    }
}
