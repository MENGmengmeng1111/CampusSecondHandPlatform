package com.sdut.campussecondhand.controller;

import com.sdut.campussecondhand.entity.User;
import com.sdut.campussecondhand.service.UserService;
import com.sdut.campussecondhand.util.MD5Util;
import com.sdut.campussecondhand.util.Result;
import com.sdut.campussecondhand.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
/**
 * 用户模块控制器
 * 负责用户注册、登录、实名认证、密码找回等功能
 * @author 孟冠宇
 * @date 2026-05-18
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 用户手机号注册
     * @param user 用户信息（phone、password）
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        // 校验手机号是否已注册
        User existUser = userService.getUserByPhone(user.getPhone());
        if (existUser != null) {
            return Result.error("该手机号已注册");
        }

        // 密码加密（SHA-256）
        user.setPassword(MD5Util.encrypt(user.getPassword()));
        // 设置初始认证状态为未认证
        user.setAuthStatus(0);
        
        boolean success = userService.save(user);
        if (success) {
            return Result.success("注册成功");
        } else {
            return Result.error("注册失败，请稍后重试");
        }
    }
    /**
     * 用户登录
     * @param user 用户信息（phone、password）
     * @return 登录结果（包含JWT令牌）
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        // 密码加密后查询
        String encryptedPassword = MD5Util.encrypt(user.getPassword());
        User loginUser = userService.getUserByPhoneAndPassword(user.getPhone(), encryptedPassword);
        
        if (loginUser == null) {
            return Result.error("手机号或密码错误");
        }

        // 生成JWT令牌
        String token = JwtUtil.generateToken(loginUser.getId().toString());
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("userId", loginUser.getId());
        data.put("authStatus", loginUser.getAuthStatus());
        
        return Result.success("登录成功", data);
    }
    /**
     * 校园实名认证
     * @param userId 用户ID
     * @param studentId 学号/工号
     * @param realName 真实姓名
     * @param college 学院/部门
     * @param cardImage 校园卡照片URL
     * @return 认证结果
     */
    @PostMapping("/auth")
    public Result authenticate(
            @RequestParam Long userId,
            @RequestParam String studentId,
            @RequestParam String realName,
            @RequestParam String college,
            @RequestParam String cardImage) {
        
        User user = userService.getById(userId);
        if (user == null) {
            return Result.error("用户不存在");
        }

        // 更新认证信息
        user.setStudentId(studentId);
        user.setRealName(realName);
        user.setCollege(college);
        user.setCardImage(cardImage);
        // 设置认证状态为待审核
        user.setAuthStatus(1);
        
        boolean success = userService.updateById(user);
        if (success) {
            return Result.success("认证申请已提交，等待审核");
        } else {
            return Result.error("提交失败，请稍后重试");
        }
    }

    /**
     * 密码找回
     * @param phone 手机号
     * @param code 短信验证码
     * @param newPassword 新密码
     * @return 重置结果
     */
    @PostMapping("/resetPassword")
    public Result resetPassword(
            @RequestParam String phone,
            @RequestParam String code,
            @RequestParam String newPassword) {
        
        // 校验短信验证码（此处简化，实际需对接短信服务）
        if (!"123456".equals(code)) {
            return Result.error("验证码错误");
        }

        User user = userService.getUserByPhone(phone);
        if (user == null) {
            return Result.error("该手机号未注册");
        }

        // 更新密码
        user.setPassword(MD5Util.encrypt(newPassword));
        boolean success = userService.updateById(user);
        if (success) {
            return Result.success("密码重置成功");
        } else {
            return Result.error("重置失败，请稍后重试");
        }
    }

    /**
     * 获取用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/info")
    public Result getUserInfo(@RequestParam Long userId) {
        User user = userService.getById(userId);
        if (user == null) {
            return Result.error("用户不存在");
        }
        // 隐藏敏感信息
        user.setPassword(null);
        return Result.success(user);
    }
}
