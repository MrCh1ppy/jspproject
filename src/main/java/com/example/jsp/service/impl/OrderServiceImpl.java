package com.example.jsp.service.impl;


import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.manager.toservice.OrderManager;
import com.example.jsp.pojo.Order;
import com.example.jsp.pojo.OrderInfo;
import com.example.jsp.pojo.Product;
import com.example.jsp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderManager orderManager;


	@Autowired
	public void setOrderManager (OrderManager orderManager) {
		this.orderManager = orderManager;
	}


	@Override
	public void create (Order order) throws ProjectException {
		try {
			orderManager.insert(order);
		} catch (SonElementNotExistExceptionOld sonElementNotExistExceptionOld) {
			throw new ProjectException(sonElementNotExistExceptionOld.toString(), 305);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void create (Order order, String[] numList, String[] idList) throws ProjectException {
		if (idList.length != numList.length) {
			throw new ProjectException("参数长度不匹配", 701);
		}
		create(order);
		try {
			for (int i = 0; i < numList.length; i++) {
				orderManager.addProduct(order, Integer.parseInt(idList[i]), Integer.parseInt(numList[i]));
			}
		} catch (SonElementNotExistExceptionOld elementNotExistExceptionOld) {
			throw new ProjectException("创建订单时,没有对应的商品", 702);
		}
	}


	@Override
	public void delete (Order order) throws ProjectException {
		orderManager.destroy(order);
	}

	@Override
	public void delete (Integer id) throws ProjectException {
		orderManager.destroy(id);
	}

	@Override
	public void restore (Order order) throws ProjectException {
		try {
			orderManager.restore(order);
		} catch (SonElementNotExistExceptionOld e) {
			throw new ProjectException(e.toString(), 302);
		}
	}

	@Override
	public Order select (Integer orderId) throws ProjectException {
		return orderManager.select(orderId);
	}

	@Override
	public List<Order> select () {
		return orderManager.select();
	}


	@Override
	public OrderService addProduct (Order order, Integer productId, Integer num) throws ProjectException {
		try {
			orderManager.addProduct(order, productId, num);
		} catch (SonElementNotExistExceptionOld sonElementNotExistExceptionOld) {
			sonElementNotExistExceptionOld.printStackTrace();
		}
		restore(order);
		return this;
	}

	@Override
	public OrderService addProduct (Order order, Product product, Integer num) throws ProjectException {
		try {
			orderManager.addProduct(order, product, num);
		} catch (SonElementNotExistExceptionOld sonElementNotExistExceptionOld) {
			sonElementNotExistExceptionOld.printStackTrace();
		}
		restore(order);
		return this;
	}


	@Override
	public OrderService addException (Order order, OrderInfo orderInfo) throws ProjectException {
		orderManager.addOrderInfo(order, orderInfo);
		restore(order);
		return this;
	}

}
