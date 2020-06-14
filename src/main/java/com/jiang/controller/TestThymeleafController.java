package com.jiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author 蒋雨岳
 * @Date 2020/3/24 0024
 */
@Controller
public class TestThymeleafController {


    @GetMapping("/testThymeleaf")
    public  String testThymeleaf(Map  map){
        map.put("key","value");
        return "test2";
    }
    @GetMapping("/Test")
    public  String test(Map  map){
        map.put("key","value");
        return "Test";
    }

//    @GetMapping("/toLogin")
//    public  String toLogin(Map  map){
//        map.put("key","value");
//        return "login";
//    }

    @GetMapping("/index")
    public  String index(){

        return  "index";
    }
}
