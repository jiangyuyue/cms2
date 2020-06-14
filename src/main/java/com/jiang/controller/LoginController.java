package com.jiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 蒋雨岳
 * @Date 2020/6/8 0008
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping("/toLogin")
    public String LoginIndex(){
        return "index";
    }
}
