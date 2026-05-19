package com.sdut.campussecondhand.service;

import com.sdut.campussecondhand.entity.Product;

import java.util.List;

/**
 * 物品服务接口
 */
public interface ProductService {

    /**
     * 发布物品
     */
    boolean publishProduct(Product product);

    /**
     * 删除物品（逻辑删除）
     */
    boolean deleteProduct(Long productId, Long userId);

    /**
     * 更新物品信息
     */
    boolean updateProduct(Product product);

    /**
     * 根据ID获取物品详情
     */
    Product getProductById(Long productId);

    /**
     * 获取用户发布的物品列表
     */
    List<Product> getUserProducts(Long userId);

    /**
     * 获取所有在售物品
     */
    List<Product> getOnSaleProducts();

    /**
     * 根据分类获取物品
     */
    List<Product> getProductsByCategory(String category);

    /**
     * 搜索物品
     */
    List<Product> searchProducts(String keyword);

    /**
     * 增加浏览次数
     */
    void incrementViewCount(Long productId);
}