package com.example.jsp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

/**
 * @author 橙鼠鼠
 */
@Getter
@JsonIgnoreProperties(value = {"handler"})
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Deliver implements Serializable {
	private Integer id;
	private String name;
	private String telephone;
	private User loginUser;


	public Deliver setName (String name) {
		this.name = name;
		return this;
	}

	public Deliver setTelephone (String telephone) {
		this.telephone = telephone;
		return this;
	}

	public Deliver setLoginUser (User loginUser) {
		this.loginUser = loginUser;
		return this;
	}
}
