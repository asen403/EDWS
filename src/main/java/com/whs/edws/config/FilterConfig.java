package com.whs.edws.config;


import com.whs.edws.filter.TokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class FilterConfig {

    /**
     * 注册token过滤器
     *
     * @return {@link FilterRegistrationBean}<{@link TokenFilter}>
     */
    @Bean
    public FilterRegistrationBean<TokenFilter> registerTokenFiler(){
        // 注册filter
        FilterRegistrationBean<TokenFilter> bean = new FilterRegistrationBean<>();
        // 设置执行优先级
        bean.setOrder(1);
        // 设置具体filter
        bean.setFilter(new TokenFilter());
        // 设置哪些路径执行此过滤器
        bean.addUrlPatterns("/project/*");
        return bean;
    }


    /**
     * 注册跨域问题过滤器
     *
     * @return {@link FilterRegistrationBean}<{@link CorsFilter}>
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> registerCorsFilter(){
        //1. 添加 CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //放行哪些原始域
        config.addAllowedOriginPattern("*");
        //是否发送 Cookie
        config.setAllowCredentials(true);
        //放行哪些请求方式
        config.addAllowedMethod("*");
        //放行哪些原始请求头部信息
        config.addAllowedHeader("*");
        //暴露哪些头部信息
        config.addExposedHeader("*");
        //2. 添加映射路径
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", config);
        //3. 返回新的CorsFilter
        CorsFilter corsFilter = new CorsFilter(corsConfigurationSource);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>();
        bean.setOrder(0);
        bean.setFilter(corsFilter);
        return bean;
    }
}
