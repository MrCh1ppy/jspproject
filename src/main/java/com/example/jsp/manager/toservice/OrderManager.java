package com.example.jsp.manager.toservice;

import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.pojo.Order;
import com.example.jsp.pojo.OrderInfo;
import com.example.jsp.pojo.Product;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface OrderManager {
	Integer insert (Order target) throws SonElementNotExistExceptionOld;

	void destroy (Integer id);

	void destroy (Order order);

	Order select (Integer id);

	List<Order> select ();

	Integer restore (Order target) throws SonElementNotExistExceptionOld;

	Integer getId (Order target);

	Boolean isNotExist (Integer id);

	OrderManager addProduct (Order target, Product product, int num) throws SonElementNotExistExceptionOld;

	OrderManager addProduct (Order target, int productId, int num) throws SonElementNotExistExceptionOld;

	OrderManager linkedInsert (Order target) throws SonElementNotExistExceptionOld;

	OrderManager linkedRestore (Order order) throws SonElementNotExistExceptionOld;

	OrderManager linkedDestroy (Order target);

	OrderManager linkedDestroy (int orderId);

	OrderManager addOrderInfo (Order target, OrderInfo orderInfo);

	List<Order> selectByStatus (Integer status);
}
