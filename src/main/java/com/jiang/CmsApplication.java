package com.jiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class CmsApplication {

    public static void main(String[] args) {
        System.out.println("ddddd");
        SpringApplication.run(CmsApplication.class, args);
    }

}
