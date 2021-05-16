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
public class Guest implements Serializable {
    private Integer id;
    private String name;
    private String telephone;
    private User loginUser;
    private List<Address> addresses;
}

/*
 *
 * */
