package com.example.jsp.manager.todao;

import com.example.jsp.pojo.ProductPackage;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface ProductPackageManagerToDao {
    int save(ProductPackage target);

    void delete(int id);

    void deleteByOrderId(int id);

    ProductPackage select(int id);

    void update(ProductPackage target);

    List<ProductPackage> selectByOrderId(int id);

    Integer getId(ProductPackage productPackage);
}
