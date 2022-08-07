package com.anderson.apitdd.exception;

public class BadRequestGeneric extends RuntimeException{
    public BadRequestGeneric(String message) {
        super(message);
    }
}
