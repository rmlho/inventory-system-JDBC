package org.example.model;

public class Product {
    protected int id;
    protected String productName;
    protected CategoryProductEnum productCategory;
    protected int availableQuantity;
    protected int minimumQuantity;
    protected float productValue;
    protected String productStatus;

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
        return 0;
    }

    public void setProductCategory(CategoryProductEnum productCategory) {
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
