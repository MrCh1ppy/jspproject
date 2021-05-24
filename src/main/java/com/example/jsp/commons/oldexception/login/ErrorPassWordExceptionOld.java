package com.example.jsp.commons.oldexception.login;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 橙鼠鼠
 */
@Getter
@Setter
public class ErrorPassWordExceptionOld extends LoginExceptionOld {

	/**
	 * Constructs a new exception with {@code null} as its detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 */
	public ErrorPassWordExceptionOld () {
	}

	/**
	 * Constructs a new exception with the specified detail message.  The
	 * cause is not initialized, and may subsequently be initialized by
	 * a call to {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for
	 *                later retrieval by the {@link #getMessage()} method.
	 */
	public ErrorPassWordExceptionOld (String message) {
		super(message);
	}
}
