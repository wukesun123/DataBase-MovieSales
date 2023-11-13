package com.example.springboot.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Film;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
//依赖注入：@Resource 注解可以将依赖的资源注入到类中，使得类能够使用这些资源，而不需要手动创建或获取。
//    注入名称匹配：@Resource 注解可以根据名称匹配来注入资源，默认情况下会根据变量名或方法名来匹配。如果没有明确指定 name 属性，它会按照以下顺序匹配：
//    根据变量名（字段名）匹配。
//    根据方法名匹配（去掉 get、set 等前缀，将剩余部分作为名称匹配）。
//    支持类型匹配：@Resource 注解也可以根据类型匹配来注入资源，如果名称匹配失败，它会尝试根据类型匹配。
//    按照名称注入：通过 name 属性指定要注入的资源名称，这样可以明确指定要注入的资源，不依赖于默认的名称匹配。
    UserMapper userMapper; // 注入 UserMapper 对象，用于数据库操作
//     分页查询用户列表，pageNum  当前页码，默认为 1，pageSize 每页显示数量，默认为 10 ，search   搜索关键字，默认为空字符串，@return 分页查询结果
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if (StringUtils.isNotBlank(search)) {
            wrapper.like(User::getUsername, search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<User>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }
//用户登录接口，user 包含用户名和密码的 User 对象，return登录结果，成功返回用户信息，失败返回错误信息
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword()));
        if (res == null) {
            return Result.error("-1", "用户名或密码错误");
        }
        return Result.success(res);
    }
//根据用户名查询用户信息
    @GetMapping("/selectUsername")
    public User selectUsername(@RequestParam("username") String username) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        User user = userMapper.selectOne(wrapper);
        return user;
    }
// 插入新用户，@return 插入结果，成功返回成功信息，失败返回错误信息
    @PostMapping
    public Result insert(@RequestBody User user) {
        userMapper.insert(user);
        return Result.success();
    }
//更新用户信息，user 包含用户信息的 User 对象，return 更新结果，成功返回成功信息，失败返回错误信息
    @PutMapping
    public Result update(@RequestBody User user) {
        userMapper.updateById(user);
        return Result.success();
    }
//根据用户ID删除用户，@param id 用户ID，return 删除结果，成功返回成功信息，失败返回错误信息
    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        userMapper.deleteById(id);
        return Result.success();
    }
}
