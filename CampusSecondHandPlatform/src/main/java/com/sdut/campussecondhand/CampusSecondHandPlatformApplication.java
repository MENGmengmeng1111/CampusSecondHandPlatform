package com.sdut.campussecondhand;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 * @author 孟冠宇
 * @date 2026-05-18
 */
@SpringBootApplication
@MapperScan("com.sdut.campussecondhand.mapper") // 扫描所有Mapper接口
public class CampusSecondHandPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusSecondHandPlatformApplication.class, args);
        System.out.println("=====================================");
        System.out.println("校园二手闲置物品交易平台启动成功！");
        System.out.println("访问地址：http://localhost:8080");
        System.out.println("=====================================");
    }
}