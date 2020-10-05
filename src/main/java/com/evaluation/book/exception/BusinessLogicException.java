package com.evaluation.book.exception;

public class BusinessLogicException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String message;

    public BusinessLogicException(String message) {
        super(message);
    }
}
