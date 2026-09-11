package com.inventory.product.dao;

import com.inventory.product.model.CategoryProductEnum;
import com.inventory.product.model.Product;

import java.util.List;

public interface ProductDAO {
    void insert(Product product);
    List<Product> generalReport();
    void update(int id, Product product);
    void remove(int id);
    List<Product> searchName(String name);
    List<Product> higherValue();
    List<Product> pricePerProduct();
    List<Product> searchCategory(CategoryProductEnum category);
}
