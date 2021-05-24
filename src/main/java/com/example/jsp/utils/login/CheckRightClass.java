package com.example.jsp.utils.login;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Component
public class CheckRightClass implements StpInterface {
	@Override
	public List<String> getPermissionList (Object o, String s) {
		return null;
	}

	@Override
	public List<String> getRoleList (Object o, String s) {
		var loginId = (LoginId) o;
		List<String> roleList;
		try {
			String[] split = loginId.toStringByReflect().split(",");
			roleList = Arrays.asList(split);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
			roleList=new ArrayList<>();
		}
		System.out.println("->角色列表"+roleList);
		return roleList;
	}
}
