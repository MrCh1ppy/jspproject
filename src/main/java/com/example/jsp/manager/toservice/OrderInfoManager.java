package com.example.jsp.manager.toservice;

import com.example.jsp.pojo.OrderInfo;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface OrderInfoManager {
	Integer insert (OrderInfo target);

	void destroy (Integer id);

	void destroy (OrderInfo orderInfo);

	OrderInfo select (Integer id);

	void deleteByOrderId (Integer id);

	Integer restore (OrderInfo target);

	List<OrderInfo> selectByOrderId (Integer id);

	Integer getId (OrderInfo orderInfo);
}
