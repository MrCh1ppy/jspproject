package com.example.jsp.manager.impl;

import com.example.jsp.commons.exception.manager.ProjectException;
import com.example.jsp.commons.exception.manager.SonElementNotExistException;
import com.example.jsp.dao.OrderDao;
import com.example.jsp.manager.todao.OrderManagerToDao;
import com.example.jsp.manager.toservice.*;
import com.example.jsp.pojo.Order;
import com.example.jsp.pojo.OrderInfo;
import com.example.jsp.pojo.ProductPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class OrderManagerImpl implements OrderManagerToDao, OrderManager {
    private OrderDao orderDao;
    private GuestManager guestManager;
    private AddressManager addressManager;
    private StoreManager storeManager;
    private ProductPackageManager productPackageManager;
    private ProductManager productManager;
    private DeliverManager deliverManager;
    private OrderInfoManager orderInfoManager;

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Autowired
    public void setGuestManager(GuestManager guestManager) {
        this.guestManager = guestManager;
    }

    @Autowired
    public void setAddressManager(AddressManager addressManager) {
        this.addressManager = addressManager;
    }

    @Autowired
    public void setStoreManager(StoreManager storeManager) {
        this.storeManager = storeManager;
    }

    @Autowired
    public void setProductPackageManager(ProductPackageManager productPackageManager) {
        this.productPackageManager = productPackageManager;
    }

    @Autowired
    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    @Autowired
    public void setDeliverManager(DeliverManager deliverManager) {
        this.deliverManager = deliverManager;
    }

    @Autowired
    public void setOrderInfoManager(OrderInfoManager orderInfoManager) {
        this.orderInfoManager = orderInfoManager;
    }

    @Override
    public Integer save(Order target) {
        return orderDao.save(target);
    }

    @Override
    public void delete(Integer id) {
        orderDao.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insert(Order target) throws ProjectException {
        exist(target);
        Integer id = getId(target);
        if (id == null) {
            int save = save(target);
            target.setId(save);
            for (OrderInfo orderInfo : target.getOrderInfos()) {
                int i = orderInfoManager.insert(orderInfo);
                orderInfo.setId(i);
            }
            for (ProductPackage productPackage : target.getProductPackages()) {
                productPackage.setOrder(target);
                if (productManager.isNotExist(productPackage.getProduct().getId())) {
                    throw new SonElementNotExistException("product");
                }
                int i = productPackageManager.insert(productPackage);
                productPackage.setId(i);
            }
            return save;
        }
        target.setId(id);
        return target.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void destroy(Integer id) {
        Order select = select(id);
        if (select != null) {
            destroy(select(id));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void destroy(Order order) {
        orderInfoManager.deleteByOrderId(order.getId());
        productPackageManager.deleteByOrderId(order.getId());
        delete(order.getId());
    }

    @Override
    public Order select(Integer id) {
        return orderDao.selectById(id);
    }

    @Override
    public List<Order> select() {
        return orderDao.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer restore(Order target) throws ProjectException {
        Integer id = getId(target);
        if (id == null) {
            exist(target);
            productPackageManager.deleteByOrderId(target.getId());
            orderInfoManager.deleteByOrderId(target.getId());
            for (ProductPackage aPackage : target.getProductPackages()) {
                int i = productPackageManager.insert(aPackage);
                aPackage.setId(i);
            }
            for (OrderInfo orderInfo : target.getOrderInfos()) {
                int insert = orderInfoManager.insert(orderInfo);
                orderInfo.setId(insert);
            }
            orderDao.update(target);
            return 0;
        }
        target.setId(id);
        return target.getId();
    }

    @Override
    public void update(Order target) {
        orderDao.update(target);
    }

    @Override
    public Integer getId(Order target) {
        return orderDao.getId(target);
    }

    @Override
    public Boolean isNotExist(Integer id) {
        return select(id) == null;
    }


    private void exist(Order target) throws ProjectException {
        if (guestManager.isNotExist(target.getGuest().getId())) {
            throw new SonElementNotExistException("guest");
        }
        if (storeManager.isNotExist(target.getStore().getId())) {
            throw new SonElementNotExistException("store");
        }
        if (deliverManager.isNotExist(target.getDeliver().getId())) {
            throw new SonElementNotExistException("deliver");
        }
        if (addressManager.isNotExist(target.getAddress().getId())) {
            throw new SonElementNotExistException("address");
        }
    }
}
