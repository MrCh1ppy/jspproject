package com.example.jsp.manager.toservice;

import com.example.jsp.pojo.ProductPackage;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface ProductPackageManager {
    int insert(ProductPackage target);

    void destroy(int id);

    void destroy(ProductPackage productPackage);

    void deleteByOrderId(int id);

    ProductPackage select(int id);

    int restore(ProductPackage target);

    List<ProductPackage> selectByOrderId(int id);

    Integer getId(ProductPackage productPackage);

    boolean isNotExist(int id);
}
