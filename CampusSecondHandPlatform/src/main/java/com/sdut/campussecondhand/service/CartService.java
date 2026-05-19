package com.sdut.campussecondhand.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdut.campussecondhand.entity.Cart;

import java.util.List;
/**
 * 购物车服务接口
 */
public interface CartService extends IService<Cart> {
    /**
     * 根据用户ID查询购物车列表
     * @param userId 用户ID
     * @return 购物车列表
     */
    List<Cart> getCartByUserId(Long userId);

    /**
     * 添加商品到购物车
     * @param cart 购物车对象
     * @return 是否添加成功
     */
    boolean addToCart(Cart cart);

    /**
     * 从购物车删除商品
     * @param id 购物车ID
     * @return 是否删除成功
     */
    boolean removeFromCart(Long id);
}
