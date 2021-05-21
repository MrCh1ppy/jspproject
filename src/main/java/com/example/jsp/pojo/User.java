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
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private Integer enabled;

    public User setId (Integer id) {
        this.id = id;
        return this;
    }

    public User setUsername (String username) {
        this.username = username;
        return this;
    }

    public User setPassword (String password) {
        this.password = password;
        return this;
    }

    public User setEnabled (Integer enabled) {
        this.enabled = enabled;
        return this;
    }
}
