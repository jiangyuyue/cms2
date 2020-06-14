package com.jiang.config.shiroOauth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiang.common.exception.RRException;
import com.jiang.common.utils.R;
import com.jiang.models.SysUserTokenEntity;
import com.jiang.models.User;
import com.jiang.service.ShiroService;
import com.jiang.service.UserService;
import com.jiang.service.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author 蒋雨岳
 * @Date 2020/3/23 0023
 */
@Component
public class OAuth2Realm  extends AuthorizingRealm {

    @Autowired
    private ShiroService  shiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AuthenticationToken;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("******************执行授权逻辑");


        User user = (User)principals.getPrimaryPrincipal();
        Long userId = user.getId();

        //用户权限列表
        //用户有多个角色  角色有多个菜单权限
        Set<String> permsSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return null;
    }


    @Autowired
    UserService userService;

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("******************执行认证逻辑");

        UsernamePasswordToken mytoken=(UsernamePasswordToken) token;
        String username=mytoken.getUsername();
        String password =String.valueOf(mytoken.getPassword());

        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
//        if (user==null){
//            return  null;
//        }
//        if (!user.getPassword().equals(password)){
//            throw  new RRException("密码错误！");
//        }



//
//        String accessToken = (String) token.getPrincipal();
//
//
//        //根据accessToken，查询用户信息
//        SysUserTokenEntity tokenEntity = shiroService.queryByToken(accessToken);
//        //token失效
//        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
//            throw new IncorrectCredentialsException("token失效，请重新登录");
//        }
//
//        //查询用户信息
//        User user = shiroService.queryUser(tokenEntity.getUserId());
////        //账号锁定
////        if(user.getStatus() == 0){
////            throw new LockedAccountException("账号已被锁定,请联系管理员");
////        }
//
        AuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
      // SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
