package com.whs.edws;

import com.whs.edws.entity.District;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

@SpringBootTest
class EdwsApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testRedis(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("sex", "man");
        System.out.println(ops.get("sex"));
    }

    @Test
    void testRedisSerializer(){
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        District district = new District();
        district.setId(1);
        district.setName("西湖区");
        valueOperations.set("district", district);
    }

}
