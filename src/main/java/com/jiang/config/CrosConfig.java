package com.jiang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author 蒋雨岳
 * @Date 2020/3/21 0021
 */
@Configuration
public class CrosConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      //映射资源路径
    }


    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
             registry.addInterceptor(new LogHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(new String[]{"/user/**"});
    }

}
