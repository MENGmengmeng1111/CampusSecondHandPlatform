package com.sdut.campussecondhand.common;

import com.sdut.campussecondhand.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 捕获所有Controller层异常，返回统一格式的错误信息
 * @author 孟冠宇
 * @date 2026-05-18
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 捕获所有异常
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        logger.error("系统异常：", e);
        return Result.error("系统内部错误，请稍后重试");
    }

    /**
     * 捕获空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public Result<?> handleNullPointerException(NullPointerException e) {
        logger.error("空指针异常：", e);
        return Result.error("请求参数错误，请检查");
    }
}