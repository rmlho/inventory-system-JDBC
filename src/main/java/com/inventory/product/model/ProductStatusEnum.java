package com.inventory.product.model;

public enum ProductStatusEnum {
    ACTIVE("ACTIVE"),
    UNAVAILABLE("UNAVAILABLE"),
    DISCONTINUED("DISCONTINUED");

    private String status;

    @Override
    public String toString() {
        return status;
    }

    ProductStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
