package com.example.jsp.manager.toservice;

import com.example.jsp.pojo.Address;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface AddressManager {
	List<Address> select ();

	Address select (Integer id);

	Integer insert (Address address);

	void destroy (Integer id);

	void destroy (Address address);

	Integer restore (Address address);

	List<Address> selectByGuestId (Integer id);

	void dropByGuestId (Integer id);

	Boolean isNotExist (Integer id);

	Integer getId (Address address);

	Integer inOrder(Integer addressId);
}
