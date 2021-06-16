package com.example.jsp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author 橙鼠鼠
 */
@ToString
@Getter
@JsonIgnoreProperties(value = {"handler"})
@AllArgsConstructor
@NoArgsConstructor
public class Guest implements Serializable {
	private Integer id;
	private String name;
	private String telephone;
	private User loginUser;
	private List<Address> addresses;

	public Guest setId (Integer id) {
		this.id = id;
		return this;
	}

	public Guest setName (String name) {
		this.name = name;
		return this;
	}

	public Guest setTelephone (String telephone) {
		this.telephone = telephone;
		return this;
	}

	public Guest setLoginUser (User loginUser) {
		this.loginUser = loginUser;
		return this;
	}

	public Guest setAddresses (List<Address> addresses) {
		this.addresses = addresses;
		return this;
	}
}

/*
 *
 * */
