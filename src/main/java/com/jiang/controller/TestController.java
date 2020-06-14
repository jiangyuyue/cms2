package com.jiang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Hashtable;

/**
 * @author 蒋雨岳
 * @Date 2020/3/21 0021
 */
@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response)  throws  Exception{


      //  Hashtable
        response.getWriter().write("hello cms!");
    }



}
