package com.example.jsp.manager.impl;

import com.example.jsp.dao.ProductPackageDao;
import com.example.jsp.manager.todao.ProductPackageManagerToDao;
import com.example.jsp.manager.toservice.ProductPackageManager;
import com.example.jsp.pojo.ProductPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class ProductPackageManagerImpl implements ProductPackageManagerToDao, ProductPackageManager {
    private ProductPackageDao productPackageDao;

    @Autowired
    public void setProductPackageDao(ProductPackageDao productPackageDao) {
        this.productPackageDao = productPackageDao;
    }


    @Override
    public int save(ProductPackage target) {
        return productPackageDao.save(target);
    }

    @Override
    public void delete(int id) {
        productPackageDao.delete(id);
    }

    @Override
    public int insert(ProductPackage target) {
        Integer id = getId(target);
        if(id==null){
            int save = save(target);
            target.setId(save);
            return save;
        }
        return id.intValue();
    }

    @Override
    public void destroy(int id) {
        delete(id);
    }

    @Override
    public void destroy(ProductPackage productPackage) {
        delete(productPackage.getId());
    }

    @Override
    public void deleteByOrderId(int id) {
        productPackageDao.deleteByOrderId(id);
    }

    @Override
    public ProductPackage select(int id) {
        return productPackageDao.selectById(id);
    }

    @Override
    public int restore(ProductPackage target) {
        Integer id = getId(target);
        if(id==null){
            update(target);
            return 0;
        }
        return id.intValue();
    }

    @Override
    public void update(ProductPackage target) {
        productPackageDao.update(target);
    }

    @Override
    public List<ProductPackage> selectByOrderId(int id) {
        return productPackageDao.selectByOrderId(id);
    }

    @Override
    public Integer getId(ProductPackage productPackage) {
        return productPackageDao.getId(productPackage);
    }

    @Override
    public boolean isNotExist(int id) {
        return select(id)==null;
    }
}
