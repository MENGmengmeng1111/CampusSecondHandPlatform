package com.sdut.campussecondhand.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户实体类
 * 对应数据库表：user
 * @author 孟冠宇
 * @date 2026-05-19
 */
@Data
@TableName("user")
public class User {

    /**
     * 用户ID（主键自增）
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 手机号（登录账号）
     */
    private String phone;

    /**
     * 密码（SHA-256加密存储）
     */
    private String password;

    /**
     * 校园学号/工号
     */
    private String studentId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 所在学院/部门
     */
    private String college;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 校园卡照片URL（实名认证用）
     */
    private String cardImage;

    /**
     * 认证状态：0-未认证 1-待审核 2-已认证 3-认证失败
     */
    private Integer authStatus;

    /**
     * 信用分（初始100分）
     */
    private Integer creditScore;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}