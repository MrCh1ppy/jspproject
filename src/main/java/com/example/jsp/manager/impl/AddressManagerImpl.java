package com.example.jsp.manager.impl;

import com.example.jsp.dao.AddressDao;
import com.example.jsp.manager.todao.AddressManagerToDao;
import com.example.jsp.manager.toservice.AddressManager;
import com.example.jsp.pojo.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class AddressManagerImpl implements AddressManagerToDao, AddressManager {
	private AddressDao addressDao;

	@Override
	public List<Address> select () {
		return addressDao.selectAll();
	}

	@Override
	public Address select (Integer id) {
		return addressDao.selectById(id);
	}

	@Override
	public Integer save (Address address) {
		addressDao.save(address);
		return address.getId();
	}

	@Override
	public void delete (Integer id) {
		addressDao.delete(id);
	}

	@Override
	public void update (Address address) {
		addressDao.update(address);
	}

	@Override
	public List<Address> selectByGuestId (Integer id) {
		return addressDao.selectByGuestId(id);
	}

	@Override
	public void dropByGuestId (Integer id) {
		addressDao.dropByGuestId(id);
	}

	@Override
	public Integer getId (Address address) {
		return addressDao.getId(address);
	}

	@Override
	public Integer inOrder (Integer addressId) {
		return addressDao.inOrder(addressId);
	}

	@Override
	/**@apiNote :会在数据库寻找是否有相同的数据,如果有则返回那个数据的id,反之则返回新插入数据的id
	 * */
	public Integer insert (Address address) {
		Integer id = addressDao.getId(address);
		if (id == null) {
			save(address);
			return address.getId();
		}
		address.setId(id);
		return address.getId();
	}

	@Override
	public void destroy (Integer id) {
		addressDao.delete(id);
	}

	@Override
	public void destroy (Address address) {
		addressDao.delete(address.getId());
	}

	@Override
	public Integer restore (Address address) {
		Integer id = addressDao.getId(address);
		if (id == null) {
			addressDao.update(address);
			return 0;
		}
		address.setId(id);
		return address.getId();
	}

	@Override
	public Boolean isNotExist (Integer id) {
		return addressDao.selectById(id) == null;
	}

	@Autowired
	public void setAddressDao (AddressDao addressDao) {
		this.addressDao = addressDao;
	}
}
