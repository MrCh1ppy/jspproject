package com.example.jsp.manager.todao;

import com.example.jsp.pojo.OrderInfo;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface OrderInfoManagerToDao {
	Integer save (OrderInfo target);

	void delete (Integer id);

	OrderInfo select (Integer id);

	void update (OrderInfo target);

	List<OrderInfo> selectByOrderId (Integer id);

	Integer getId (OrderInfo orderInfo);

	Boolean isNotExist (Integer id);

	void deleteByOrderId (Integer id);
}
