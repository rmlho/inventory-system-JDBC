package com.inventory.product;

import com.inventory.product.dao.ProductDAO;
import com.inventory.product.dao.ProductDAOImpl;
import com.inventory.product.exception.ProductInvalidException;
import com.inventory.product.model.CategoryProductEnum;
import com.inventory.product.model.Product;
import com.inventory.product.model.ProductStatusEnum;
import com.inventory.product.validation.ProductValidator;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static com.inventory.product.validation.ProductValidator.validateEmptyName;

public class Main {
    static void main(String[] args) {

    }

    static void registerProduct() {
            Scanner sc = new Scanner(System.in);

        System.out.println("=-=-=-=-=-=-=-=-=-=- REGISTER =-=-=-=-=-=-=-=-=-=-");
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
            ProductStatusEnum status = ProductStatusEnum.valueOf(sc.nextLine().toUpperCase());

            sc.close();

            Product product = new Product(name, cat, availableQuantity, miniQuantity, value, status);
            ProductDAO dao = new ProductDAOImpl();
            dao.insert(product);

    }

    static void tableList() {
        System.out.println("=-=-=-=-=-=-=-=-=-=- PRODUCTS TABLE =-=-=-=-=-=-=-=-=-=-");
        ProductDAO dao = new ProductDAOImpl();
        List<Product> products = dao.generalReport();

        for (Product p : products) {
            p.toString();
        }
    }

    static void updateTable() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=-=-=-=-=-=-=-=-=-=- UPDATE =-=-=-=-=-=-=-=-=-=-");
        System.out.println("ID Product: ");
        int id = sc.nextInt();
        sc.close();

        ProductDAO dao = new ProductDAOImpl();
        dao.update(id, dao.findCode(id));
    }

    static void removeTable() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=-=-=-=-=-=-=-=-=-=- REMOVE =-=-=-=-=-=-=-=-=-=-");
        System.out.println("ID product: ");
        int id = sc.nextInt();
        sc.close();

        ProductDAO dao = new ProductDAOImpl();
        dao.remove(id);
    }

    static void listSeachName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=-=-=-=-=-=-=-=-=-=- PRODUCTS NAME TABLE =-=-=-=-=-=-=-=-=-=-");
        System.out.println("Product Name: ");
        String name = sc.nextLine();
        sc.close();

        ProductDAO dao = new ProductDAOImpl();
        List<Product> products = dao.searchName(name);

        for (Product p : products) {
            p.toString();
        }
    }

    static void listhigherValue() {
        System.out.println("=-=-=-=-=-=-=-=-=-=- PRODUCTS HIGHER VALUE TABLE =-=-=-=-=-=-=-=-=-=-");

        ProductDAO dao = new ProductDAOImpl();
        List<Product> products = dao.higherValue();

        for (Product p : products) {
            p.toString();
        }
    }

    static void listpricePerProduct() {
        System.out.println("=-=-=-=-=-=-=-=-=-=- PRICE PER PRODUCT TABLE =-=-=-=-=-=-=-=-=-=-");

        ProductDAO dao = new ProductDAOImpl();
        List<Product> products = dao.pricePerProduct();

        for (Product p : products) {
            p.toString();
        }
    }

    static void listsearchCategory() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=-=-=-=-=-=-=-=-=-=- PRODUCT CATEGORY TABLE =-=-=-=-=-=-=-=-=-=-");
        System.out.println("Code Category Product: ");
        int cat = sc.nextInt();

        sc.close();

        ProductDAO dao = new ProductDAOImpl();
        List<Product> products = dao.searchCategory(CategoryProductEnum.fromId(cat));

        for (Product p : products) {
            p.toString();
        }
    }
}
