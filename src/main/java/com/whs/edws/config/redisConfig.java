package com.whs.edws.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class redisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置redis连接工厂
        redisTemplate.setConnectionFactory(factory);
        // 设置key的序列化器
        FastJsonRedisSerializer redisSerializer = new FastJsonRedisSerializer(Object.class);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置value的序列化器
        redisTemplate.setValueSerializer(redisSerializer);
        // 返回设置参数的redisTemplate
        return redisTemplate;
    }
}
