package com.similiz.dictionary.exception_handler;

public class NoSuchWordException extends RuntimeException {
    public NoSuchWordException(String message) {
        super(message);
    }
}
