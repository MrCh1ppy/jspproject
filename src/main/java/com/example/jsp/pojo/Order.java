package com.example.jsp.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author 橙鼠鼠
 */
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    public Order setId (Integer id) {
        this.id = id;
        return this;
    }

    public Order setOrderInfos (List<OrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
        return this;
    }

    public Order setGuest (Guest guest) {
        this.guest = guest;
        return this;
    }

    public Order setDeliver (Deliver deliver) {
        this.deliver = deliver;
        return this;
    }

    public Order setStore (Store store) {
        this.store = store;
        return this;
    }

    public Order setAddress (Address address) {
        this.address = address;
        return this;
    }

    public Order setStatus (Integer status) {
        this.status = status;
        return this;
    }

    public Order setMessage (String message) {
        this.message = message;
        return this;
    }

    public Order setProductPackages (List<ProductPackage> productPackages) {
        this.productPackages = productPackages;
        return this;
    }

    public Order setTime (String time) {
        this.time = time;
        return this;
    }
}
