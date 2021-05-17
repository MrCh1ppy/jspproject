package com.example.jsp.commons.model;

import lombok.*;

import java.io.Serializable;

/**
 * @author 橙鼠鼠
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Token implements Serializable {
	private String username;
	private String password;
	private String userType;
}
