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
