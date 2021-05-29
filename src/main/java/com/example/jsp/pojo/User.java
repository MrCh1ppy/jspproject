package com.example.jsp.pojo;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 橙鼠鼠
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private Integer enabled;
}
