package com.jiang.config;

import com.jiang.config.shiroOauth.OAuth2Filter;
import com.jiang.config.shiroOauth.OAuth2Realm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author 蒋雨岳
 * @Date 2020/3/23 0023
 */
@Configuration
public class ShrioConfig {

    //1.创建ShiroFilterFactoryBean
    //2. 创建DefaultWebSecurityManager
    //3. 创建Realm（需要自定义）

    @Bean("securityManager")  //安全管理器 关联 Realm
    public SecurityManager securityManager(OAuth2Realm oAuth2Realm){

        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(oAuth2Realm);
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // "anon": 无需认证 （登录）可以访问
       // authc   必须认证才能访问  oauth2
        // user : 如果施工remeberMe的功能可以直接访问
        // perms : 该资源必须得到资源权限才可以访问
        //  role :该资源必须得到角色全下才可以访问

        //oauth过滤
        Map<String, Filter> filters = new HashMap<>();

        //dadad
        filters.put("oauth2", new OAuth2Filter());
        shiroFilterFactoryBean.setFilters(filters);
        // shiroFilter.setFilterChainDefinitionMap(filterMap);

        Map<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/webjars/**", "anon");
//        filterMap.put("/druid/**", "anon");
//        filterMap.put("/app/**", "anon");
//        filterMap.put("/sys/login", "anon");
//        filterMap.put("/swagger/**", "anon");
//        filterMap.put("/imgs/**", "anon");
//        filterMap.put("/v2/api-docs", "anon");
//        filterMap.put("/swagger-ui.html", "anon");
//        filterMap.put("/swagger-resources/**", "anon");
//        filterMap.put("/captcha.jpg", "anon");
//        filterMap.put("/getVerificationCode", "anon");
//        filterMap.put("/getVerificationCodeWeb", "anon");
//        filterMap.put("bu/bucustorder/export", "anon");
//        /* filterMap.put("/user/login", "anon");*/
//        filterMap.put("/user/driverLogin", "anon");
//        filterMap.put("/user/custLogin", "anon");
//        // filterMap.put("/user/getLoginUserInfo", "anon");
//        filterMap.put("/tms/**", "anon");
//        filterMap.put("/sys/jsp/**","anon");
//        filterMap.put("/sys/cost/alipay/**", "anon");
//        filterMap.put("/user/loginweb", "anon");
//        filterMap.put("/sys/user/websave", "anon");
        filterMap.put("/user/**","anon");
        filterMap.put("/test/**","anon");
        filterMap.put("/index/**","anon");
        filterMap.put("/testThymeleaf/**","anon"); //放行
//        filterMap.put("/aaa.txt", "anon");
        filterMap.put("/**", "authc");

//shiro
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        return shiroFilterFactoryBean;
    }

//    @Bean("lifecycleBeanPostProcessor")
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager);
//        return advisor;
//    }


    public static void main(String[] args) {
        List<Integer>  list=new ArrayList<Integer>();
        list.add(3);
        list.add(4);
        list.add(6);
        list.add(1);
        list.add(1);

        for (int i = 0; i < list.size(); i++) {

            for (int j = 0; j <list.size()-1-i; j++) {

                if (list.get(j).byteValue()>list.get(j+1).byteValue()){
                    int temp=list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,temp);
//                    　arr[j]=arr[j+1];
//　　　　　　　　　　arr[j+1]=temp;
                }
            }
        }

        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i).byteValue());
        }
    }
}
