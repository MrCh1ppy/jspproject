package com.example.jsp.commons.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author 橙鼠鼠
 */
@Getter
@Setter
@Accessors(chain = true)
@Component
public class Transporter {
	private HashMap<String, Object> data;
	private String msg;
	private int code;

	public Transporter () {
		this.setCode(0);
		this.data = new HashMap<>();
	}

	public void fail (int errorCode, String msg) {
		this.code = errorCode;
		this.msg = msg;
	}

	public Transporter addData (String key, Object value) {
		this.getData().put(key, value);
		return this;
	}
	/*0 is success otherwise boom!!!!!*/
}
