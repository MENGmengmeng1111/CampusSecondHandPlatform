package com.sdut.campussecondhand.controller;

import com.sdut.campussecondhand.entity.Cart;
import com.sdut.campussecondhand.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 购物车控制器
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 根据用户ID查询购物车列表
     * @param userId 用户ID
     * @return 购物车列表
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<Cart>> getCartByUserId(@PathVariable Long userId) {
        List<Cart> cartList = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cartList);
    }

    /**
     * 添加商品到购物车
     * @param cart 购物车对象
     * @return 操作结果
     */
    @PostMapping
    public ResponseEntity<String> addToCart(@RequestBody Cart cart) {
        boolean success = cartService.addToCart(cart);
        if (success) {
            return ResponseEntity.ok("添加购物车成功");
        } else {
            return ResponseEntity.badRequest().body("添加购物车失败");
        }
    }

    /**
     * 删除购物车商品
     * @param id 购物车ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long id) {
        boolean success = cartService.removeFromCart(id);
        if (success) {
            return ResponseEntity.ok("删除购物车商品成功");
        } else {
            return ResponseEntity.badRequest().body("删除购物车商品失败");
        }
    }
}
