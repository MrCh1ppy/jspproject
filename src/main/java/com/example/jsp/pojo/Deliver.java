package com.example.jsp.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * @author 橙鼠鼠
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Deliver extends User implements Serializable {
    private Integer id;
    private String name;
    private String telephone;
    private User loginUser;

    @Override
    public Deliver setId (Integer id) {
        this.id = id;
        return this;
    }

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
