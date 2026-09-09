package org.example.validation;

import org.example.exception.ProductInvalidException;

public final class ProductValidator {

    public ProductValidator() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    public static void validateEmptyName(String name) throws ProductInvalidException {
        if (name == null || name.isBlank()) {
            throw new ProductInvalidException("Empty field.");
        }
    }

    public static void validateNegative(int quatity) throws ProductInvalidException {
        if (quatity < 0) {
            throw new ProductInvalidException("A field cannot be negative.");
        }
    }

    public static void validateValue(float value) throws ProductInvalidException {
        if (value <= 0) {
            throw new ProductInvalidException("The value must be greater than zero.");
        }
    }

    public static void confirmDeletion(int quantity) throws ProductInvalidException {
        if (quantity != 0) {
            throw new ProductInvalidException("A product cannot have an available quantity.");
        }
    }

    public static void validatorSufficientInventory(int quantity, int availableQuantity) throws ProductInvalidException {
        if (quantity > availableQuantity) {
            throw new ProductInvalidException("Do not have that quantity in stock.");
        }
    }


}
