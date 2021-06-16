package com.example.jsp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 橙鼠鼠
 */
@Getter
@JsonIgnoreProperties(value = {"handler"})
@AllArgsConstructor
@NoArgsConstructor
public class ProductPackage implements Serializable {
	private Integer id;
	private Integer num;
	private Product product;
	private Order order;

	public ProductPackage setId (Integer id) {
		this.id = id;
		return this;
	}

	public ProductPackage setNum (Integer num) {
		this.num = num;
		return this;
	}

	public ProductPackage setProduct (Product product) {
		this.product = product;
		return this;
	}

	public ProductPackage setOrder (Order order) {
		this.order = order;
		return this;
	}

	@Override
	public String toString () {
		return "ProductPackage{" +
				"id=" + id +
				", num=" + num +
				", product=" + product +
				", order=" + order.getId() +
				'}';
	}
}
