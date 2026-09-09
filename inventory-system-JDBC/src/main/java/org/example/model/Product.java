package org.example.model;

import java.util.Objects;

public abstract class Product {
    protected int id;
    protected String productName;
    protected int productCategory;
    protected int availableQuantity;
    protected int minimumQuantity;
    protected float productValue;
    protected String productStatus;

    public Product(String productName, int productCategory, int availableQuantity, int minimumQuantity, float productValue) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.availableQuantity = availableQuantity;
        this.minimumQuantity = minimumQuantity;
        this.productValue = productValue;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productCategory=" + productCategory +
                ", availableQuantity=" + availableQuantity +
                ", minimumQuantity=" + minimumQuantity +
                ", productValue=" + productValue +
                ", productStatus='" + productStatus + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(int minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public float getProductValue() {
        return productValue;
    }

    public void setProductValue(float productValue) {
        this.productValue = productValue;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
}
