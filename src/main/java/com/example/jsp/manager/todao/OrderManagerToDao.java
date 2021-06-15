package com.example.jsp.manager.todao;

import com.example.jsp.pojo.Order;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface OrderManagerToDao {
	Integer save (Order target);

	void delete (Integer id);

	Order select (Integer id);

	List<Order> select ();

	void update (Order target);

	Integer getId (Order target);

	List<Order> selectByStatus (Integer status);
}
