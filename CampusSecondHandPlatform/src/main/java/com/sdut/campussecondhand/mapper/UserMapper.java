package com.sdut.campussecondhand.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdut.campussecondhand.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据访问层
 * 继承MyBatis-Plus的BaseMapper，自动拥有基础CRUD方法
 * @author 孟冠宇
 * @date 2026-05-19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}