package com.example.jsp.dao;

import com.example.jsp.pojo.ProductPackage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
public interface ProductPackageDao {
    int save(ProductPackage target);

    void delete(int id);

    ProductPackage selectById(int id);

    List<ProductPackage> selectAll();

    void update(ProductPackage target);

    List<ProductPackage> selectByOrderId(int id);
}
