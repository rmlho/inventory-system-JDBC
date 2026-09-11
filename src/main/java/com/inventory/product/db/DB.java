package com.inventory.product.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static String url = "jdbc:postgresql://localhost:5432/dbproduct";
    private static String username = "postgres";
    private static String password = "Lov31070601";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(username, username, username);
        } catch (SQLException e) {
            throw new RuntimeException("ERRO: " + e.getMessage(), e);
        }
    }
}
