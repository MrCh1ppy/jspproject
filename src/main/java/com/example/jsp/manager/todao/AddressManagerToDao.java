package com.example.jsp.manager.todao;

import com.example.jsp.pojo.Address;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface AddressManagerToDao {
	List<Address> select ();

	Address select (Integer id);

	Integer save (Address address);

	void delete (Integer id);

	void update (Address address);

	List<Address> selectByGuestId (Integer id);

	void dropByGuestId (Integer id);

	Integer getId (Address address);

	Integer inOrder(Integer addressId);
}
