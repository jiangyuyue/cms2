package com.jiang.config.shiroOauth;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author 蒋雨岳
 * @Date 2020/3/23 0023
 */
public class OAuth2Token implements AuthenticationToken {//AuthenticationToken

    private String token;

    public OAuth2Token(String token){
        this.token = token;
    }
    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
