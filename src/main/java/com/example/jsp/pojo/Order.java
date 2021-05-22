package com.example.jsp.pojo;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
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

    public Order () {
        String time= LocalDateTime.now().toString();
    }
}
