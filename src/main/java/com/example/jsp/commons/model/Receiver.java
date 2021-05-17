package com.example.jsp.commons.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 橙鼠鼠
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Receiver<T> extends Transporter<T>{
    private Token token;
}
