package com.example.jsp.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * @author 橙鼠鼠
 */
@ToString
@Getter
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
}
