package com.example.springboot.common;

public class Result<T> {
    // HTTP 状态码
    private String code;
    // 响应消息
    private String msg;
    // 传输的数据
    private T data;
    public Result() {
    }
    // 带参构造函数，用于初始化响应数据
    public Result(T data) {
        this.data = data;
    }
    // 获取 HTTP 状态码
    public String getCode() {
        return code;
    }
    // 设置 HTTP 状态码
    public void setCode(String code) {
        this.code = code;
    }
    // 获取响应消息
    public String getMsg() {
        return msg;
    }
    // 设置响应消息
    public void setMsg(String msg) {
        this.msg = msg;
    }
    // 获取响应数据
    public T getData() {
        return data;
    }
    // 设置响应数据
    public void setData(T data) {
        this.data = data;
    }
    // 静态方法，用于创建成功的响应对象（无数据）
    public static Result success() {
        Result result = new Result<>();
        result.setCode("0");   // 成功状态码通常为 "0"
        result.setMsg("成功"); // 设置默认成功消息
        return result;
    }
    // 静态方法，用于创建成功的响应对象（带数据）
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("0");   // 成功状态码通常为 "0"
        result.setMsg("成功"); // 设置默认成功消息
        return result;
    }
    // 静态方法，用于创建失败的响应对象
    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code); // 设置自定义状态码
        result.setMsg(msg);   // 设置自定义错误消息
        return result;
    }
}
