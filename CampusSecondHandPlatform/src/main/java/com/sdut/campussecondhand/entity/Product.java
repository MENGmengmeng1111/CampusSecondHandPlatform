package com.sdut.campussecondhand.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 物品实体类
 */
public class Product {
    private Long id;
    private Long userId;          // 发布用户ID
    private String title;         // 物品标题
    private String description;   // 物品描述
    private BigDecimal price;     // 价格
    private String category;      // 分类
    private String images;        // 图片URL，多个用逗号分隔
    private Integer status;       // 状态：0-下架，1-在售，2-已售出，3-已删除
    private Integer viewCount;    // 浏览次数
    private Integer likeCount;    // 点赞次数
    private String location;      // 所在位置
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 构造方法
    public Product() {}

    public Product(Long userId, String title, String description, BigDecimal price,
                   String category, String images, String location) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.images = images;
        this.location = location;
        this.status = 1;
        this.viewCount = 0;
        this.likeCount = 0;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    // Getter和Setter方法
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public Integer getViewCount() { return viewCount; }
    public void setViewCount(Integer viewCount) { this.viewCount = viewCount; }

    public Integer getLikeCount() { return likeCount; }
    public void setLikeCount(Integer likeCount) { this.likeCount = likeCount; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }

    public LocalDateTime getUpdateTime() { return updateTime; }
    public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
}