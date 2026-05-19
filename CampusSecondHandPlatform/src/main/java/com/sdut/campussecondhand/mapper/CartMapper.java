package com.sdut.campussecondhand.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdut.campussecondhand.entity.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 购物车Mapper接口
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    /**
     * 根据用户ID查询购物车列表
     * @param userId 用户ID
     * @return 购物车列表
     */
    List<Cart> selectByUserId(Long userId);
}