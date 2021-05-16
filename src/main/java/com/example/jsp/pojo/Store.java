package com.example.jsp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 橙鼠鼠
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store implements Serializable {
    private Integer id;
    private String name;
    private String address;
    private String telephone;
    private User user;
}
