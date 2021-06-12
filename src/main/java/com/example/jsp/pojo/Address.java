package com.example.jsp.pojo;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 橙鼠鼠
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Address implements Serializable {
	private String addressString;
	private Integer id;
	private Integer guestId;
}
