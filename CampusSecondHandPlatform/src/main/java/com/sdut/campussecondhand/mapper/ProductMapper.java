package com.sdut.campussecondhand.mapper;

import com.sdut.campussecondhand.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 物品数据访问接口
 */
@Mapper
public interface ProductMapper {

    @Insert("INSERT INTO product(user_id, title, description, price, category, images, status, view_count, like_count, location, create_time, update_time) " +
            "VALUES(#{userId}, #{title}, #{description}, #{price}, #{category}, #{images}, #{status}, #{viewCount}, #{likeCount}, #{location}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Product product);

    @Delete("DELETE FROM product WHERE id = #{id}")
    int deleteById(Long id);

    @Update("UPDATE product SET title=#{title}, description=#{description}, price=#{price}, " +
            "category=#{category}, images=#{images}, status=#{status}, location=#{location}, update_time=#{updateTime} WHERE id=#{id}")
    int update(Product product);

    @Select("SELECT * FROM product WHERE id = #{id}")
    Product selectById(Long id);

    @Select("SELECT * FROM product WHERE user_id = #{userId} ORDER BY create_time DESC")
    List<Product> selectByUserId(Long userId);

    @Select("SELECT * FROM product WHERE status = 1 ORDER BY create_time DESC")
    List<Product> selectAllOnSale();

    @Select("SELECT * FROM product WHERE category = #{category} AND status = 1 ORDER BY create_time DESC")
    List<Product> selectByCategory(String category);

    @Update("UPDATE product SET view_count = view_count + 1 WHERE id = #{id}")
    int incrementViewCount(Long id);

    @Update("UPDATE product SET like_count = like_count + 1 WHERE id = #{id}")
    int incrementLikeCount(Long id);

    @Select("SELECT * FROM product WHERE title LIKE CONCAT('%', #{keyword}, '%') OR description LIKE CONCAT('%', #{keyword}, '%')")
    List<Product> searchByKeyword(String keyword);
}