package com.jiang.service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiang.common.exception.RRException;
import com.jiang.common.utils.R;
import com.jiang.mapper.UserMapper;
import com.jiang.models.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 蒋雨岳
 * @Date 2020/3/20 0020
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements  UserService{

    @Autowired
    StringRedisTemplate redisTemplate;


    //username  表示 用户名 或者电话号码
    @Override
    public R Login(String username,String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return R.error("用户名或者密码不能为空！");
        }
//        User user = this.getOne(new QueryWrapper<User>().eq("username", username));
//        if (user==null){
//            return R.error("用户名不存在！");
//        }
//        if (!user.getPassword().equals(password)){
//            return  R.error("密码输入错误！");
//        }
        AuthenticationToken Atoken = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();

        try{
            subject.login(Atoken);
        }catch (AuthenticationException e){

            throw  new RRException("登录异常！");

        }
//        //生成token存入redis
//        String token= UUID.randomUUID().toString().replaceAll("-","");
//        redisTemplate.opsForValue().set(token, JSON.toJSONString(user));

        //讲token返回客户端，可以保存进cookie
        return R.ok();
    }

    //注册
    @Override
    //String phone,String  username,String password,String ConfirmPasswword,String company
    public R zhuce(User user,String Code) {

        //跳过其他校验

        if (!user.getPassword().equals(user.getConfirmPasswword())){
            return R.error("两次密码不一致请重新输入！");
        }
        //从redis中取出验证码Code
        String redisCode=redisTemplate.opsForValue().get("verifyCode:"+user.getPhone());
        if (StringUtils.isEmpty(redisCode)){
            return R.error("验证码已经失效！请重新获取！");
        }
        if (!Code.equals(redisCode)){
            return R.error("验证码错误!");
        }

        //生成用户user  记录
        if (!this.save(user))  throw new  RRException(" 插入失败！");

        return R.ok();
    }

    //手机号获取验证码(模拟手机短信)
    @Override
    public R getCodeByPhone(String phone) {

        if (StringUtils.isEmpty(phone)){
            return  R.error("手机号不能为空");
        }

        //修改用正则表达式
        if (!StringUtils.isNumber(phone)){
            return R.error("手机号格式错误，请重新输入");
        }

        //生成验证码，六位随机数
        String verifyCode = String.valueOf(new Random().nextInt(8999) + 1000);

        //验证码存入redis 设置有效期
        String key="verifyCode:"+phone;
        redisTemplate.opsForValue().set(key,verifyCode,60L, TimeUnit.SECONDS);


        return R.ok().put("verifyCode",verifyCode);
    }
}
