package com.inventory.product.exception;

public class ProductInvalidException extends RuntimeException {
    public ProductInvalidException(String message) {
        super(message);
    }
}