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
    public int save(Product target) {
        return productDao.save(target);
    }

    @Override
    public void delete(int id) {
        productDao.delete(id);
    }

    @Override
    public int insert(Product target) throws ProjectException {
        if (storeManager.isNotExist(target.getStore().getId())) {
            throw new SonElementNotExistException();
        }
        Integer id = getId(target);
        if(id==null){
            int save = save(target);
            target.setId(save);
            return save;
        }else {
            return id;
        }
    }

    @Override
    public void destroy(int id) {
        delete(id);
    }

    @Override
    public void destroy(Product target) {
        destroy(target.getId());
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
    public int restore(Product target) throws ProjectException {
        Integer id = getId(target);
        if(id==null){
            if (storeManager.isNotExist(target.getStore().getId())) {
                throw new SonElementNotExistException();
            }
            update(target);
            return 0;
        }
        return id.intValue();
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
    public boolean isNotExist(int id) {
        return select(id)==null;
    }
}
