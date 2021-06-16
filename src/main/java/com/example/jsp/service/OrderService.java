package com.example.jsp.service;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.pojo.Order;
import com.example.jsp.pojo.OrderInfo;
import com.example.jsp.pojo.Product;

import java.util.List;


public interface OrderService {
	void create (Order order) throws ProjectException;


	void create (Order order, String[] numList, String[] idList) throws ProjectException;

	void delete (Order order) throws ProjectException;


	void delete (Integer id) throws ProjectException;

	void restore (Order order) throws ProjectException;

	Order select (Integer orderId) throws ProjectException;

	List<Order> select ();

	OrderService addProduct (Order order, Integer productId, Integer num) throws ProjectException;

	OrderService addProduct (Order order, Product product, Integer num) throws ProjectException;

	OrderService addException (Order order, OrderInfo orderInfo) throws ProjectException;


}
