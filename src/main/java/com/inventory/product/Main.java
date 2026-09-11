package com.inventory.product;

import com.inventory.product.dao.ProductDAO;
import com.inventory.product.dao.ProductDAOImpl;
import com.inventory.product.exception.ProductInvalidException;
import com.inventory.product.model.CategoryProductEnum;
import com.inventory.product.model.Product;
import com.inventory.product.model.ProductStatusEnum;
import com.inventory.product.validation.ProductValidator;

import java.util.Locale;
import java.util.Scanner;

import static com.inventory.product.validation.ProductValidator.validateEmptyName;

public class Main {
    static void main(String[] args) {

    }

    static void registerProduct() {
            Scanner sc = new Scanner(System.in);

            System.out.println("=-=-=-=-=- Categories =-=-=-=-=-");
            for (CategoryProductEnum cat : CategoryProductEnum.values()) {
                System.out.printf("  [%d] - %-35s \n", cat.getId(), cat.getName());
            }

            System.out.println("Product Name: ");
            String name = sc.nextLine();

            ProductValidator.validatorString(name);

            System.out.println("Product Category: ");
            CategoryProductEnum cat = CategoryProductEnum.fromId(sc.nextInt());

            System.out.println("Available Quantity: ");
            int availableQuantity = sc.nextInt();

            ProductValidator.validateNegative(availableQuantity);

            System.out.println("Minimum Quantity: ");
            int miniQuantity = sc.nextInt();

            ProductValidator.validateNegative(miniQuantity);

            System.out.println("Product Value: ");
            float value = sc.nextFloat();

            ProductValidator.validateValue(value);
            sc.nextLine();

            System.out.println("=-=-=-=-=- STATUS =-=-=-=-=-");
            for (ProductStatusEnum s : ProductStatusEnum.values()) {
                System.out.printf(" %-35s \n", s.getStatus());
            }

            System.out.println("Status: ");
            String status = sc.nextLine().toUpperCase();

            sc.close();

            Product product = new Product(name, cat, availableQuantity, miniQuantity, value, status);
            ProductDAO dao = new ProductDAOImpl();
            dao.insert(product);

    }
}
