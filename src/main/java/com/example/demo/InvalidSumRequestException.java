package com.example.demo;

public class InvalidSumRequestException extends RuntimeException {

    public InvalidSumRequestException(String message) {
        super(message);
    }
}
