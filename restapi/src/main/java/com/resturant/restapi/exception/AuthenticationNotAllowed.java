package com.resturant.restapi.exception;

public class AuthenticationNotAllowed extends RuntimeException{
    public AuthenticationNotAllowed(String message) {
        super(message);
    }
}
