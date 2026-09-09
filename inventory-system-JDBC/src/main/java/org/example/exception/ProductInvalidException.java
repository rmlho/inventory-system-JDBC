package org.example.exception;

public class ProductInvalidException extends Exception {
    public ProductInvalidException(String message) {
        super(message);
    }
}