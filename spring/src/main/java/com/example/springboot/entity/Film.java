package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("spider")
@Data
public class Film {

    @TableId(type = IdType.AUTO)
    private Integer id; // 电影的唯一标识符
    private String title; // 电影标题
    private String link; // 电影链接
    private String imgsrc; // 电影封面图片链接
    private String rating; // 电影评分
    private String judgenum; // 评价人数
    private String inq; // 影片概述
    private String director; // 导演姓名
    private String scriptwriter; // 编剧姓名
    private String type; // 电影类型
    private String status; // 电影地区
    private String language; // 语言
    private String date; // 上映日期
    private String time; // 上映时间
}

