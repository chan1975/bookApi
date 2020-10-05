package com.evaluation.book.exception;

public class RecordNotFoundException extends Exception{

    private String message;

    public RecordNotFoundException(String message) {
        super(message);
    }
}
