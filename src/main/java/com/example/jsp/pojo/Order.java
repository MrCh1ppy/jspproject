package com.example.jsp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Data
@JsonIgnoreProperties(value = {"handler"})
@Accessors(chain = true)
@AllArgsConstructor
public class Order implements Serializable {
	private Integer id;
	private List<OrderInfo> orderInfos;
	private Guest guest;
	private Deliver deliver;
	private Store store;
	private Address address;
	private Integer status;
	private String message;
	private List<ProductPackage> productPackages;
	private String time;

    public Order() {
        time=LocalDateTime.now().toString();
        productPackages=new LinkedList<>();
        orderInfos=new LinkedList<>();
    }
}
