package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("order") // 表名为 "order"
@Data
public class Order {
    private Integer userid; // 用户ID，关联User实体类
    private Integer filmid; // 电影ID，关联Film实体类
    private Integer num; // 购买数量
    private Double price; // 单价
}
