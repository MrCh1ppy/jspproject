package com.example.jsp.manager.impl;

import com.example.jsp.commons.exception.manager.ProjectException;
import com.example.jsp.commons.exception.manager.SonElementNotExistException;
import com.example.jsp.dao.ProductDao;
import com.example.jsp.manager.todao.ProductManagerToDao;
import com.example.jsp.manager.toservice.ProductManager;
import com.example.jsp.manager.toservice.StoreManager;
import com.example.jsp.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class ProductManagerImpl implements ProductManagerToDao, ProductManager {
    private StoreManager storeManager;
    private ProductDao productDao;

    @Autowired
    public void setStoreManager(StoreManager storeManager) {
        this.storeManager = storeManager;
    }

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Integer save(Product target) {
        return productDao.save(target);
    }

    @Override
    public void delete(Integer id) {
        productDao.delete(id);
    }

    @Override
    public Integer insert(Product target) throws ProjectException {
        if (storeManager.isNotExist(target.getStore().getId())) {
            throw new SonElementNotExistException();
        }
        Integer id = getId(target);
        if (id == null) {
            int save = save(target);
            target.setId(save);
            return save;
        }
        target.setId(id);
        return target.getId();
    }

    @Override
    public void destroy(Integer id) {
        delete(id);
    }

    @Override
    public void destroy(Product target) {
        destroy(target.getId());
    }

    @Override
    public Product select(Integer id) {
        return productDao.selectById(id);
    }

    @Override
    public List<Product> select() {
        return productDao.selectAll();
    }

    @Override
    public Integer restore(Product target) throws ProjectException {
        Integer id = getId(target);
        if (id == null) {
            if (storeManager.isNotExist(target.getStore().getId())) {
                throw new SonElementNotExistException();
            }
            update(target);
            return 0;
        }
        target.setId(id);
        return target.getId();
    }

    @Override
    public void update(Product target) {
        productDao.update(target);
    }

    @Override
    public Integer getId(Product product) {
        return productDao.getId(product);
    }

    @Override
    public Boolean isNotExist(Integer id) {
        return select(id) == null;
    }
}
