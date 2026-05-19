package com.sdut.campussecondhand.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdut.campussecondhand.entity.User;
import com.sdut.campussecondhand.mapper.UserMapper;
import com.sdut.campussecondhand.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户服务实现类
 * 实现用户模块的业务逻辑
 * @author 孟冠宇
 * @date 2026-05-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getUserByPhone(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        return getOne(queryWrapper);
    }

    @Override
    public User getUserByPhoneAndPassword(String phone, String encryptedPassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        queryWrapper.eq("password", encryptedPassword);
        return getOne(queryWrapper);
    }

    @Override
    public boolean updateAuthStatus(Long userId, Integer authStatus) {
        User user = getById(userId);
        if (user == null) {
            return false;
        }
        user.setAuthStatus(authStatus);
        user.setUpdateTime(new Date());
        return updateById(user);
    }

    /**
     * 重写save方法，自动设置创建时间和初始信用分
     */
    @Override
    public boolean save(User user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setCreditScore(100); // 初始信用分100
        user.setAuthStatus(0); // 初始未认证
        return super.save(user);
    }

    /**
     * 重写updateById方法，自动更新更新时间
     */
    @Override
    public boolean updateById(User user) {
        user.setUpdateTime(new Date());
        return super.updateById(user);
    }
}