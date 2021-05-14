package com.example.jsp.manager.impl;

import com.example.jsp.commons.exception.SonElementContradictionException;
import com.example.jsp.dao.ProductDao;
import com.example.jsp.pojo.Product;
import com.example.jsp.manager.ProductManager;
import com.example.jsp.manager.StoreManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class ProductManagerImpl implements ProductManager {
    private ProductDao productDao;
    private StoreManager storeManager;

    @Autowired
    public void setStoreManager(StoreManager storeManager) {
        this.storeManager = storeManager;
    }

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(Product target) throws SonElementContradictionException {
        try {
            int savePre = storeManager.savePre(target.getStore());
            target.getStore().setId(savePre);
            return productDao.save(target);
        } catch (SonElementContradictionException e) {
            throw new SonElementContradictionException("product." + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(int id) {
        var select = select(id);
        storeManager.delete(select.getId());
        productDao.delete(id);
    }

    @Override
    public Product select(int id) {
        return productDao.selectById(id);
    }

    @Override
    public List<Product> select() {
        return productDao.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Product target) throws SonElementContradictionException {
        try {
            int i = storeManager.updatePre(target.getStore());
            target.getStore().setId(i);
            productDao.update(target);
        } catch (SonElementContradictionException e) {
            throw new SonElementContradictionException("product." + e.getMessage());
        }
    }
}
