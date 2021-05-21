package com.example.jsp.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 橙鼠鼠
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private Integer id;
    private String name;
    private Double price;
    private Store store;

    public Product setId (Integer id) {
        this.id = id;
        return this;
    }

    public Product setName (String name) {
        this.name = name;
        return this;
    }

    public Product setPrice (Double price) {
        this.price = price;
        return this;
    }

    public Product setStore (Store store) {
        this.store = store;
        return this;
    }
}
