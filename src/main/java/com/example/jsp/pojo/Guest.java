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
    int id;
    String name;
    String telephone;
    User loginUser;
    private List<Address> addresses;
}

/*
 *
 * */
