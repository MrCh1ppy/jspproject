package com.example.jsp.commons.exception.manager;

/**
 * @author 橙鼠鼠
 */
public class SonElementNotExistException extends ProjectException {
    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public SonElementNotExistException() {
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public SonElementNotExistException(String message) {
        super(message);
    }
}
