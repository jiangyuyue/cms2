package com.jiang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiang.common.utils.PageResult;
import com.jiang.common.utils.R;
import com.jiang.models.User;
import com.jiang.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 蒋雨岳
 * @Date 2020/3/20 0020
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService  userService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/userLogin")
    public R  UserLogin(String username,String password){


      //  userService.get;
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        Subject subject = SecurityUtils.getSubject();
//        subject.login(token);
        return  userService.Login(username,password);
    }

//    @PostMapping("/toLogin")
//    public R  toLogin(String username,String password){
//        return  userService.Login(username,password);
//    }

    /**
     * 用户注册
     * @param user
     * @param Code
     * @return
     */
    @PostMapping("/zhuce")
    //String phone,String  username,String password,String ConfirmPasswword,String company
    public  R  zhuce(User user, String Code){
        return userService.zhuce(user,Code);
    };

    //用户注册先生成验证码
    @PostMapping("/getCodeByPhone")
    public  R getCodeByPhone(String phone){

        return userService.getCodeByPhone(phone);
    };
    /**
     *
     * @param CurrentPage  当前页码
     * @param limit    //按照每页limit 条分页
     * @return
     */
    @GetMapping("/userList")
    public  R  getUserList(int CurrentPage,int limit){
        CurrentPage=(CurrentPage==0)?1:CurrentPage;
        //传入分页参数
        PageHelper.startPage(CurrentPage,limit);

        //用户列表
        List<User> list = userService.list();
        PageInfo<User> info = new PageInfo(list);

        //页码数
        int pageNum = info.getPageNum();
        //其实就是limit
        int pageSize = info.getPageSize();
        //总记录数
        Long total=info.getTotal();
        //当前页列表
        List<User> users = info.getList();

        return  R.ok().put("userList",new PageResult<User>(pageNum,pageSize,total,users));
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @PostMapping("/update")
    public R update(User user) {
        userService.updateById(user);
        return R.ok();
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @PostMapping("/detele")
    public R detele(Long id) {
        userService.removeById(id);
        return R.ok();
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    @GetMapping("/findUser")
    public R findUser(Long id) {
        return R.ok().put("user",userService.getById(id));
    }

    /**
     * 是否启用
     * @param id
     * @param stop
     * @return
     */
    @PostMapping("/stop")
    public R ifstop(Long id,int stop) {
        User user = userService.getById(id);
        user.setStop(stop);
        userService.updateById(user);
        return R.ok();
    }
}
