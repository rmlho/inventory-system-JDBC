package com.inventory.product.validation;

import com.inventory.product.exception.ProductInvalidException;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class ProductValidator {

    public ProductValidator() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    public static void validateEmptyName(String name) {
        if (name == null || name.isBlank()) {
            throw new ProductInvalidException("Empty field.");
        }
    }

    public static void validateNegative(int quatity) {
        if (quatity < 0) {
            throw new ProductInvalidException("A field cannot be negative.");
        }
    }

    public static void validateValue(float value) {
        if (value <= 0) {
            throw new ProductInvalidException("The value must be greater than zero.");
        }
    }

    public static void confirmDeletion(int quantity) {
        if (quantity != 0) {
            throw new ProductInvalidException("A product cannot have an available quantity.");
        }
    }

    public static void validatorSufficientInventory(int quantity, int availableQuantity) {
        if (quantity > availableQuantity) {
            throw new ProductInvalidException("Do not have that quantity in stock.");
        }
    }

    public static void validatorStatusActive(String productStatus) {
        if (!Objects.equals(productStatus, "ATIVO")) {
            throw new ProductInvalidException("This product is not active.");
        }
    }

    public static void validatorString(@NotNull String text) {
        if (!text.matches("^[A-Za-zÀ-ÿ ]+$")) {
            throw new ProductInvalidException("Not is text.");
        }
    }
}
