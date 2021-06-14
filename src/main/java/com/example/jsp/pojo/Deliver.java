package com.example.jsp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 橙鼠鼠
 */
@Getter
@Setter
@JsonIgnoreProperties(value = {"handler"})
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Deliver implements Serializable {
	private Integer id;
	private String name;
	private String telephone;
	private User loginUser;
}
