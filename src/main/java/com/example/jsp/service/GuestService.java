package com.example.jsp.service;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.pojo.Address;
import com.example.jsp.pojo.Guest;
import com.example.jsp.pojo.User;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface GuestService {
	void create (Guest target) throws ProjectException;

	void delete (Guest target) throws ProjectException;

	void delete (Integer id) throws ProjectException;

	void restore (Guest guest) throws ProjectException;

	Guest select (Integer guestId) throws ProjectException;

	List<Guest> select () throws ProjectException;


	void enroll (Guest guest, User user) throws ProjectException;

	List<Address> apart (String addresses);

	void addAddress (Guest guest, Address address) throws ProjectException;

	Address getAddress (Integer addressId) throws ProjectException;

}
