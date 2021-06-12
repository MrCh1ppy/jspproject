package com.example.jsp.dao;

import com.example.jsp.pojo.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
public interface AddressDao {
	void save (@Param("target") Address target);

	void delete (@Param("id") Integer id);

	Address selectById (@Param("id") Integer id);

	List<Address> selectAll ();

	void update (@Param("target") Address target);

	List<Address> selectByGuestId (@Param("id") Integer id);

	void dropByGuestId (@Param("id") Integer id);

	Integer getId (@Param("target") Address address);
}
