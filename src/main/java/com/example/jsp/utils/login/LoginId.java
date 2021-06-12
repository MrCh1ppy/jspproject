package com.example.jsp.utils.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 橙鼠鼠
 */
@Getter
@Setter
@AllArgsConstructor
public class LoginId {
	String identity;


	public String toStringByReflect () throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		var stringBuilder = new StringBuilder();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
			var getMe = this.getClass().getMethod("get" + name);
			String value = (String) getMe.invoke(this);
			stringBuilder.append(value).append(",");
		}
		return stringBuilder.toString();
	}
}
