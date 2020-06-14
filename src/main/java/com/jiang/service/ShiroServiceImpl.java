/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.jiang.service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiang.mapper.SysMenuMapper;
import com.jiang.mapper.SysUserTokenMapper;
import com.jiang.mapper.UserMapper;
import com.jiang.models.SysMenuEntity;
import com.jiang.models.SysUserTokenEntity;
import com.jiang.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SysUserTokenMapper sysUserTokenMapper;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;
       //查找user  判断用户类型
        //系统管理员，拥有最高权限
        if(userId == 1){
            List<SysMenuEntity> menuList = sysMenuMapper.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for(SysMenuEntity menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            permsList = userMapper.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isEmpty(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
       return sysUserTokenMapper.selectOne(new QueryWrapper<SysUserTokenEntity>().eq("token",token));
       // return sysUserTokenMapper.queryByToken(token);
    }

    @Override
    public User queryUser(Long userId) {
        return userMapper.selectById(userId);
    }
}
