package com.example.jsp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    private int id;
    private List<OrderInfo> orderInfos;
    private Guest guest;
    private Deliver deliver;
    private Store store;
    private Address address;
    private int status;
    private String message;
    private List<ProductPackage> productPackages;
    private String time;
}
