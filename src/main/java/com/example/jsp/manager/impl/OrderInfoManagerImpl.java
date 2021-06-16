package com.example.jsp.manager.impl;

import com.example.jsp.dao.OrderInfoDao;
import com.example.jsp.manager.todao.OrderInfoManagerToDao;
import com.example.jsp.manager.toservice.OrderInfoManager;
import com.example.jsp.pojo.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class OrderInfoManagerImpl implements OrderInfoManagerToDao, OrderInfoManager {
	private OrderInfoDao orderInfoDao;

	@Autowired
	public void setOrderInfoDao (OrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}

	@Override
	public Integer insert (OrderInfo target) {
		Integer id = getId(target);
		if (id == null) {
			save(target);
			return target.getId();
		}
		target.setId(id);
		return target.getId();
	}

	@Override
	public Integer save (OrderInfo target) {
		orderInfoDao.save(target);
		return target.getId();
	}

	@Override
	public void delete (Integer id) {
		orderInfoDao.delete(id);
	}

	@Override
	public OrderInfo select (Integer id) {
		return orderInfoDao.selectById(id);
	}

	@Override
	public void update (OrderInfo target) {
		orderInfoDao.update(target);
	}

	@Override
	public List<OrderInfo> selectByOrderId (Integer id) {
		return orderInfoDao.selectByOrderId(id);
	}

	@Override
	public Integer getId (OrderInfo orderInfo) {
		return orderInfoDao.getId(orderInfo);
	}

	@Override
	public Boolean isNotExist (Integer id) {
		return select(id) == null;
	}

	@Override
	public void deleteByOrderId (Integer id) {
		orderInfoDao.deleteByOrder(id);
	}

	@Override
	public void destroy (Integer id) {
		delete(id);
	}

	@Override
	public void destroy (OrderInfo orderInfo) {
		destroy(orderInfo.getId());
	}

	@Override
	public Integer restore (OrderInfo target) {
		Integer id = getId(target);
		if (id == null) {
			update(target);
			return 0;
		}
		target.setId(id);
		return target.getId();
	}
}
