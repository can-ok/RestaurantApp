package com.resturant.restapi.exception;

public class ContentNotAllowed extends RuntimeException {
    public ContentNotAllowed(String message) {
        super(message);
    }
}
