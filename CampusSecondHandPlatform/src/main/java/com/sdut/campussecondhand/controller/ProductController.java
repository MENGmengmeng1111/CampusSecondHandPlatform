package com.sdut.campussecondhand.controller;

import com.sdut.campussecondhand.entity.Product;
import com.sdut.campussecondhand.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物品控制器
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 发布物品
     */
    @PostMapping("/publish")
    public ResponseEntity<Map<String, Object>> publishProduct(@RequestBody Product product) {
        boolean success = productService.publishProduct(product);
        Map<String, Object> response = new HashMap<>();
        if (success) {
            response.put("code", 200);
            response.put("message", "发布成功");
            response.put("data", product);
        } else {
            response.put("code", 400);
            response.put("message", "发布失败");
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 删除物品
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable Long productId, @RequestParam Long userId) {
        boolean success = productService.deleteProduct(productId, userId);
        Map<String, Object> response = new HashMap<>();
        if (success) {
            response.put("code", 200);
            response.put("message", "删除成功");
        } else {
            response.put("code", 400);
            response.put("message", "删除失败");
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 更新物品
     */
    @PutMapping("/{productId}")
    public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        product.setId(productId);
        boolean success = productService.updateProduct(product);
        Map<String, Object> response = new HashMap<>();
        if (success) {
            response.put("code", 200);
            response.put("message", "更新成功");
        } else {
            response.put("code", 400);
            response.put("message", "更新失败");
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 获取物品详情
     */
    @GetMapping("/{productId}")
    public ResponseEntity<Map<String, Object>> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            productService.incrementViewCount(productId);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("data", product);
        return ResponseEntity.ok(response);
    }

    /**
     * 获取用户发布的物品
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getUserProducts(@PathVariable Long userId) {
        List<Product> products = productService.getUserProducts(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("data", products);
        return ResponseEntity.ok(response);
    }

    /**
     * 获取所有在售物品
     */
    @GetMapping("/on-sale")
    public ResponseEntity<Map<String, Object>> getOnSaleProducts() {
        List<Product> products = productService.getOnSaleProducts();
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("data", products);
        return ResponseEntity.ok(response);
    }

    /**
     * 根据分类获取物品
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<Map<String, Object>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("data", products);
        return ResponseEntity.ok(response);
    }

    /**
     * 搜索物品
     */
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("data", products);
        return ResponseEntity.ok(response);
    }
}