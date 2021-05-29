package com.example.jsp.service;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.pojo.Address;
import com.example.jsp.pojo.Guest;
import com.example.jsp.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface GuestService {
	void create(Guest target)throws ProjectException;
	void enroll(Guest guest, User user)throws ProjectException;

	static List<Address> apart(String addresses) {
		List<Address>  addressList= new ArrayList<Address>();
		String[] address=addresses.split("_");
		for(int i=0;i<address.length;i+=3){
			Address temp=new Address();
			temp.setAddressString(address[i]);
			temp.setGuestId(Integer.parseInt(address[i+1]));
			temp.setId(Integer.parseInt(address[i+2]));

			addressList.add(temp);
		}

		return addressList;
	}
}
