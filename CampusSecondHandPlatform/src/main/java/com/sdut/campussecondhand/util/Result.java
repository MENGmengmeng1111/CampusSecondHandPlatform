package com.sdut.campussecondhand.util;

import lombok.Data;

/**
 * 全局统一返回结果类
 * 所有接口返回格式统一为该类
 * @author 孟冠宇
 * @date 2026-05-18
 */
@Data
public class Result<T> {
    private Integer code; // 响应码：200成功，500失败
    private String message; // 响应消息
    private T data; // 响应数据

    // 成功响应（无数据）
    public static <T> Result<T> success(String message) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage(message);
        return result;
    }

    // 成功响应（带数据）
    public static <T> Result<T> success(String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    // 失败响应
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMessage(message);
        return result;
    }
}