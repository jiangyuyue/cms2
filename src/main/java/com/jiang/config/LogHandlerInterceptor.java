package com.jiang.config;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.jiang.common.exception.RRException;
import com.jiang.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 蒋雨岳
 * @Date 2020/3/21 0021
 *
   后台管理为登录状态才可操作
 */
public class LogHandlerInterceptor  implements HandlerInterceptor {

    @Autowired
    StringRedisTemplate  redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        System.out.println("---------拦截器拦截器-----------");

        String  token=getToken(request);
//        if (StringUtils.isEmpty(token)){
//            //客户端 根据全局判断401状态 跳转登录页面
//            throw  new RRException("未登录，请先登录！",401);
//        }
//        //从redis中取出 用户
//        String userVaule = redisTemplate.opsForValue().get(token);
//         if(StringUtils.isEmpty(userVaule)){
//             throw  new RRException("未登录，请先登录！",401);
//         }
//
//        User  user=JSON.parseObject(userVaule,User.class);
//       //  if (user.getPassword().equals()){}

        return true;
    }

    //客户端请求 应将token 放在请求头或者参数中
    private String  getToken(HttpServletRequest request){
        String token=request.getParameter("token");
        if (StringUtils.isEmpty(token)){
            token=request.getHeader("token");
        }
        return token;
    }

}
