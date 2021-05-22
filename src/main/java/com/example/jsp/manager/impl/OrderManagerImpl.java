package com.example.jsp.manager.impl;

import com.example.jsp.commons.exception.manager.ProjectException;
import com.example.jsp.commons.exception.manager.SonElementNotExistException;
import com.example.jsp.dao.OrderDao;
import com.example.jsp.manager.todao.OrderManagerToDao;
import com.example.jsp.manager.toservice.*;
import com.example.jsp.pojo.Order;
import com.example.jsp.pojo.OrderInfo;
import com.example.jsp.pojo.Product;
import com.example.jsp.pojo.ProductPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
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
        orderDao.save(target);
        return target.getId();
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
            save(target);
            System.out.println("//////->"+select(target.getId()));
            orderInfoManager.deleteByOrderId(target.getId());
            productPackageManager.deleteByOrderId(target.getId());
            for (OrderInfo orderInfo : target.getOrderInfos()) {
                int i = orderInfoManager.insert(orderInfo);
                orderInfo.setOrder(target);
                orderInfo.setId(i);
            }
            for (ProductPackage productPackage : target.getProductPackages()) {
                productPackage.setOrder(target);
                Boolean notExist = productManager.isNotExist(productPackage.getProduct().getId());
                if (Boolean.TRUE.equals(notExist)) {
                    throw new SonElementNotExistException("product");
                }
                int i = productPackageManager.insert(productPackage);
                productPackage.setId(i);
            }
            return target.getId();
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
        Integer id = getId(target);
        if (id == null) {
            exist(target);
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

    @Override
    public OrderManager addProduct (Order target, Product product, int num) throws ProjectException{
        if(target.getProductPackages()==null){
            target.setProductPackages(new LinkedList<>());
        }
        Boolean notExist = productManager.isNotExist(product.getId());
        if(Boolean.TRUE.equals(notExist)){
            throw new SonElementNotExistException("order.productPackage.product");
        }
        for (ProductPackage productPackage : target.getProductPackages()) {
            if(productPackage.getProduct().equals(product)){
                productPackage.setNum(productPackage.getNum()+num);
                return this;
            }
        }
        ProductPackage aPackage = new ProductPackage().setProduct(product).setOrder(target).setNum(num);
        target.getProductPackages().add(aPackage);
        return this;
    }

    @Override
    public OrderManager addProduct (Order target, int productId, int num)throws ProjectException {
        addProduct(target,productManager.select(productId),num);
        return this;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderManager linkedInsert (Order target) throws ProjectException {
        insert(target);
        return this;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderManager linkedRestore (Order order) throws ProjectException {
        restore(order);
        return this;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderManager linkedDestroy (Order target) {
        destroy(target);
        return this;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderManager linkedDestroy (int orderId) {
        destroy(orderId);
        return null;
    }

    @Override
    public OrderManager addOrderInfo (Order target, OrderInfo orderInfo) {
        if(target.getOrderInfos()==null){
            target.setOrderInfos(new LinkedList<>());
        }
        orderInfo.setOrder(target);
        if (orderInfo.getEnabled()==null){
            orderInfo.setEnabled(1);
        }
        target.getOrderInfos().add(orderInfo);
        return this;
    }

    @Override
    @Deprecated
    public OrderManager addOrderInfo (Order target, int orderInfoId) throws ProjectException{
        OrderInfo select = orderInfoManager.select(orderInfoId);
        addOrderInfo(target,select);
        return this;
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
