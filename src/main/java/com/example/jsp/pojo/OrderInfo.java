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
public class OrderInfo implements Serializable {
	private Integer id;
	private Order order;
	private Integer enabled;
	private String message;

	public OrderInfo setId (Integer id) {
		this.id = id;
		return this;
	}

	public OrderInfo setOrder (Order order) {
		this.order = order;
		return this;
	}

	public OrderInfo setEnabled (Integer enabled) {
		this.enabled = enabled;
		return this;
	}

	public OrderInfo setMessage (String message) {
		this.message = message;
		return this;
	}

	@Override
	public String toString () {
		return "OrderInfo{" +
				"id=" + id +
				", order=" + order.getId() +
				", enabled=" + enabled +
				", message='" + message + '\'' +
				'}';
	}
}
