package com.jiang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiang.common.utils.R;
import com.jiang.models.User;

/**
 * @author 蒋雨岳
 * @Date 2020/3/20 0020
 */
public interface UserService extends IService<User> {

    //用户登录
    R  Login(String username,String password);

    //用户注册
    R  zhuce(User user,String Code);

    //用户注册先生成验证码
    R  getCodeByPhone(String phone);
}
