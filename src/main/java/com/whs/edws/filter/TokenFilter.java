package com.whs.edws.filter;

import com.alibaba.fastjson.JSON;
import com.whs.edws.common.ApiResponse;
import com.whs.edws.entity.User;
import com.whs.edws.utils.RedisUtil;
import com.whs.edws.utils.ThreadLocalUtil;
import com.whs.edws.utils.TokenUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取所有cookie
        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                flag = true;

                try{
                    // 获取token， 解析token获取用户id
                    String token = cookie.getValue();
                    String id = TokenUtil.parseToken(token);
                    // 获取redis里的用户信息
                    User user = (User) RedisUtil.get("user:" + id);
                    // 将用户信息放入ThreadLocal
                    ThreadLocalUtil.put("user", user);
                    // 放行方法
                    filterChain.doFilter(request, response);
                }catch (Exception e){
                    returnFail(response);
                }

            }
        }
        if(!flag){
            returnFail(response);
        }
    }

    private void returnFail(HttpServletResponse response) throws IOException {
        ApiResponse<String> apiResponse = ApiResponse.fail("token无效");
        String jsonString = JSON.toJSONString(apiResponse);
        response.setContentType("application/json");
        response.getWriter().write(jsonString);
    }
}
