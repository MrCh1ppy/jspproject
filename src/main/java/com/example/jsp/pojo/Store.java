package com.example.jsp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 橙鼠鼠
 */
@ToString
@Getter
@JsonIgnoreProperties(value = {"handler"})
@AllArgsConstructor
@NoArgsConstructor
public class Store implements Serializable {
	private Integer id;
	private String name;
	private String address;
	private String telephone;
	private User user;

	public Store setId (Integer id) {
		this.id = id;
		return this;
	}

	public Store setName (String name) {
		this.name = name;
		return this;
	}

	public Store setAddress (String address) {
		this.address = address;
		return this;
	}

	public Store setTelephone (String telephone) {
		this.telephone = telephone;
		return this;
	}

	public Store setUser (User user) {
		this.user = user;
		return this;
	}
}
