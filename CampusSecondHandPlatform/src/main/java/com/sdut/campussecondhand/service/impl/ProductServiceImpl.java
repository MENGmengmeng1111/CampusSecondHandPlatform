package com.sdut.campussecondhand.service.impl;

import com.sdut.campussecondhand.entity.Product;
import com.sdut.campussecondhand.mapper.ProductMapper;
import com.sdut.campussecondhand.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 物品服务实现类
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public boolean publishProduct(Product product) {
        if (product == null || product.getTitle() == null || product.getTitle().trim().isEmpty()) {
            return false;
        }
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        product.setStatus(1);
        product.setViewCount(0);
        product.setLikeCount(0);
        return productMapper.insert(product) > 0;
    }

    @Override
    public boolean deleteProduct(Long productId, Long userId) {
        Product product = productMapper.selectById(productId);
        if (product == null || !product.getUserId().equals(userId)) {
            return false;
        }
        product.setStatus(3);
        product.setUpdateTime(LocalDateTime.now());
        return productMapper.update(product) > 0;
    }

    @Override
    public boolean updateProduct(Product product) {
        if (product == null || product.getId() == null) {
            return false;
        }
        Product existing = productMapper.selectById(product.getId());
        if (existing == null) {
            return false;
        }
        product.setUpdateTime(LocalDateTime.now());
        return productMapper.update(product) > 0;
    }

    @Override
    public Product getProductById(Long productId) {
        return productMapper.selectById(productId);
    }

    @Override
    public List<Product> getUserProducts(Long userId) {
        return productMapper.selectByUserId(userId);
    }

    @Override
    public List<Product> getOnSaleProducts() {
        return productMapper.selectAllOnSale();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productMapper.selectByCategory(category);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productMapper.searchByKeyword(keyword);
    }

    @Override
    public void incrementViewCount(Long productId) {
        productMapper.incrementViewCount(productId);
    }
}