package com.inventory.product.dao;

import com.inventory.product.db.DB;
import com.inventory.product.exception.ProductInvalidException;
import com.inventory.product.model.CategoryProductEnum;
import com.inventory.product.model.Product;
import com.inventory.product.model.ProductStatusEnum;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO{
    @Override
    public void insert(Product product) {
        String sql = "INSERT INTO products(product_Name, product_Category, available_Quantity, minimum_Quantity, product_Value, product_Status) VALUES (?,?,?,?,?,?)";

        try (Connection con = DB.getConnection();
             var pt = con.prepareStatement(sql)){

            pt.setString(1, product.getProductName());
            pt.setInt(2, product.getProductCategory().getId());
            pt.setInt(3, product.getAvailableQuantity());
            pt.setInt(4, product.getMinimumQuantity());
            pt.setFloat(5, product.getProductValue());
            pt.setString(6, product.getProductStatus().toString());

            pt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> generalReport() {
        String sql = "SELECT * FROM products";
        List<Product> general = new ArrayList<>();

        try (Connection con = DB.getConnection();
            var pt = con.prepareStatement(sql);
            var eq = pt.executeQuery()){

            while (eq.next()) {
                general.add(new Product(eq.getInt("id"), eq.getString("product_Name"), CategoryProductEnum.fromId(eq.getInt("product_Category")), eq.getInt("available_Quantity"), eq.getInt("minimum_Quantity"), eq.getFloat("product_Value"), ProductStatusEnum.valueOf(eq.getString("product_Status"))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return general;
    }

    @Override
    public void update(int id, Product product) {
        String sql = "UPDATE products SET product_Name = ?, product_Category = ?, available_Quantity = ?, minimum_Quantity = ?, product_Value = ?, product_Status = ? WHERE id = ?";

        try (Connection con = DB.getConnection();
             var pt = con.prepareStatement(sql)){

            pt.setString(1, product.getProductName());
            pt.setInt(2, product.getProductCategory().getId());
            pt.setInt(3, product.getAvailableQuantity());
            pt.setInt(4, product.getMinimumQuantity());
            pt.setFloat(5, product.getProductValue());
            pt.setString(6, product.getProductStatus().toString());
            pt.setInt(7, id);

            int lines = pt.executeUpdate();
            if (lines == 0) {
                throw new SQLException("No updates performed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection con = DB.getConnection();
             var pt = con.prepareStatement(sql)){

            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> searchName(String name) {
        String sql = "SELECT * FROM products WHERE product_Name = LIKE ?";
        List<Product> general = new ArrayList<>();

        try (Connection con = DB.getConnection();
             var pt = con.prepareStatement(sql)){

            pt.setString(1, "%" + name + "%");

            try (var eq = pt.executeQuery()){
                while (eq.next()) {
                    general.add(new Product(eq.getInt("id"), eq.getString("product_Name"), CategoryProductEnum.fromId(eq.getInt("product_Category")), eq.getInt("available_Quantity"), eq.getInt("minimum_Quantity"), eq.getFloat("product_Value"), ProductStatusEnum.valueOf(eq.getString("product_Status"))));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return general;
    }

    @Override
    public List<Product> higherValue() {
        String sql = "SELECT id, product_Name, product_Value FROM products ORDER BY product_Value DESC";
        List<Product> general = new ArrayList<>();

        try (Connection con = DB.getConnection();
            var pt = con.prepareStatement(sql);
            var eq = pt.executeQuery()) {

            while (eq.next()) {
                general.add(new Product(eq.getInt("id"), eq.getString("product_Name"), eq.getFloat("product_Value")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return general;
    }

    @Override
    public List<Product> pricePerProduct() {
        String sql = "SELECT product_Name, SUM(product_Value) AS total FROM products GROUP BY product_Name";
        List<Product> general = new ArrayList<>();

        try (Connection con = DB.getConnection();
             var pt = con.prepareStatement(sql);
             var eq = pt.executeQuery()){

            while (eq.next()){
                general.add(new Product(eq.getString("product_Name"), eq.getFloat("total")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return general;
    }

    @Override
    public List<Product> searchCategory(CategoryProductEnum category) {
        String sql = "SELECT * FROM products WHERE product_Category = ?";
        List<Product> general = new ArrayList<>();

        try (Connection con = DB.getConnection();
             var pt = con.prepareStatement(sql)){

            pt.setInt(1, category.getId());

            try (var eq = pt.executeQuery()){
                while (eq.next()) {
                    general.add(new Product(eq.getInt("id"), eq.getString("product_Name"), CategoryProductEnum.fromId(eq.getInt("product_Category")), eq.getInt("available_Quantity"), eq.getInt("minimum_Quantity"), eq.getFloat("product_Value"), ProductStatusEnum.valueOf(eq.getString("product_Status"))));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return general;
    }

    @Override
    public Product findCode(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";

        try (Connection con = DB.getConnection();
             var pt = con.prepareStatement(sql)) {

            pt.setInt(1, id);

            try (var eq = pt.executeQuery()) {
                if (eq.next()) {
                    Product pr = new Product(eq.getInt("id"), eq.getString("product_Name"), CategoryProductEnum.fromId(eq.getInt("product_Category")), eq.getInt("available_Quantity"), eq.getInt("minimum_Quantity"), eq.getFloat("product_Value"), ProductStatusEnum.valueOf(eq.getString("product_Status")));
                    return pr;
                } else {
                   throw new ProductInvalidException("No product found with id: " + id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
