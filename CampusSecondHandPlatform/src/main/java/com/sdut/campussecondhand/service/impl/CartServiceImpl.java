package com.sdut.campussecondhand.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdut.campussecondhand.entity.Cart;
import com.sdut.campussecondhand.mapper.CartMapper;
import com.sdut.campussecondhand.service.CartService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 购物车服务实现类
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Override
    public List<Cart> getCartByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public boolean addToCart(Cart cart) {
        cart.setCreateTime(LocalDateTime.now());
        cart.setUpdateTime(LocalDateTime.now());
        return save(cart);
    }

    @Override
    public boolean removeFromCart(Long id) {
        return removeById(id);
    }
}