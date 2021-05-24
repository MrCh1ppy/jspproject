package com.example.jsp.commons.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author 橙鼠鼠
 */
@Getter
@Setter
@Accessors(chain = true)
public class Transporter<T> {
	private T data;
	private String msg;
	private int code;

	public Transporter () {
		this.setCode(0);
	}

	public void fail(int errorCode, String msg){
		this.code=errorCode;
		this.msg=msg;
	}
	/*0 is success otherwise boom!!!!!*/
}
