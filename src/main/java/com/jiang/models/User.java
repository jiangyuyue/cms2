package com.jiang.models;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 蒋雨岳
 * @Date 2020/3/20 0020
 */
@Data
@NoArgsConstructor
@TableName("bs_user")
public class User {
    @TableId
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String company;
    private int stop=0;
    private Integer  isParent;
    private  Long  createId;
    private  Long parentId;



    @TableField(exist = false)
    private String ConfirmPasswword;


    public User(String username,String password){

    }
    public User(String username){

    }
}
