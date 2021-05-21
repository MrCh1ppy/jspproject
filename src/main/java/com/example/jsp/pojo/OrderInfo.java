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
}
