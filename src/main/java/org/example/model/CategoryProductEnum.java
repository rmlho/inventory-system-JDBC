package org.example.model;

import org.example.exception.ProductInvalidException;

public enum CategoryProductEnum {
    HARDWARE(1, "Hardware"),
    COMPUTER(2, "Computer and Laptop"),
    ACCESSORIES(3, "Acessories and peripheral devices"),
    NETWORK(4, "Network"),
    SOFTWARE(5, "Software and licenses"),
    ENERGY(6, "Energy and Maintenance");

    private final int id;
    private final String name;

    CategoryProductEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static CategoryProductEnum fromId(int id) {
        for (CategoryProductEnum cat : values()) {
            if (cat.id == id) {
                return cat;
            }
        }

        throw new ProductInvalidException("Invalid category id : " + id);
    }
}
