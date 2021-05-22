package com.example.jsp.pojo;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 橙鼠鼠
 */
@Getter
@ToString
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Product implements Serializable {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Store store;
}
