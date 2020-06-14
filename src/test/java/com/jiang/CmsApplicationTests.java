package com.jiang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsApplicationTests {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public  void contextLoads() {

        redisTemplate.opsForValue().set("aaa","fff",1000l, TimeUnit.SECONDS);
    }



}
