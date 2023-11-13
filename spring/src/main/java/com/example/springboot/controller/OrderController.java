package com.example.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Film;
import com.example.springboot.entity.Order;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.OrderMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class OrderController {

    @Resource
    OrderMapper orderMapper; // 注入 orderMapper 对象，用于数据库操作
    @GetMapping("/list/{userid}")
    public List<Order> selectUsername(@RequestParam("userid") String userid) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userid", userid);
        List<Order> Order = orderMapper.selectList(wrapper);
        return Order;
    }

    // 插入新订单
    @PostMapping("/insert")
    public Result<?> insertOrder(@RequestBody Order order) {
        if (order != null) {
            orderMapper.insert(order);
            return Result.success("订单插入成功");
        }
        return Result.error("-1", "订单插入失败");
    }
}

