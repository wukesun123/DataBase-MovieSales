package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("usertable")
@Data
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;//用户id
    private String username;//用户名称
    private String password;//用户密码
    private String email;//用户邮箱
    private String role;//用户权限
    private Integer status;//用户状态
    private String header;//用户头像

}
