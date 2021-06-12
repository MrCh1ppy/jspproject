package com.example.jsp.commons.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author 橙鼠鼠
 */
@Getter
@Setter
@Accessors(chain = true)
public class ProjectException extends Exception {
	private Integer errorCode;

	/**
	 * Constructs a new exception with {@code null} as its detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 */
	public ProjectException (Integer errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Constructs a new exception with the specified detail message.  The
	 * cause is not initialized, and may subsequently be initialized by
	 * a call to {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for
	 *                later retrieval by the {@link #getMessage()} method.
	 */
	public ProjectException (String message, Integer errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * Constructs a new exception with {@code null} as its detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 */
	public ProjectException () {
	}


	public Integer getErrorCode () {
		return errorCode;
	}
}
