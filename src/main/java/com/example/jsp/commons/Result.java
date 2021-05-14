package com.example.jsp.commons;

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
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public static Result success() {
        var result = new Result<>();
        result.setCode(0);
        result.setMsg("success");
        return result;
    }

    public static Result success(Result result) {
        result.setMsg("success");
        result.setCode(0);
        return result;
    }

    public static Result error(Result result, int errorCode, String errorMsg) {
        result.setCode(errorCode);
        result.setMsg(errorMsg);
        return result;
    }
}
