package com.inventory.product;

import com.inventory.product.dao.ProductDAO;
import com.inventory.product.dao.ProductDAOImpl;
import com.inventory.product.exception.ProductInvalidException;
import com.inventory.product.model.CategoryProductEnum;
import com.inventory.product.model.Product;
import com.inventory.product.model.ProductStatusEnum;
import com.inventory.product.validation.ProductValidator;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static com.inventory.product.validation.ProductValidator.validateEmptyName;

public class Main {
    public static void main(String[] args) {
        int option = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            showMain();
            option = sc.nextInt();

            if (option == 0) {
                sc.close();
                return;
            }
            switch (option) {
                case 1 -> {registerProduct(sc);}
                case 2 -> {tableList();}
                case 3 -> {updateTable(sc);}
                case 4 -> {removeTable(sc);}
                case 5-> {reportMain();}
                default -> {
                    System.out.println("Choose valid option.");
                }
            }
        }
    }

    static void reportMain() {
        int option = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            showReportsMain();
            option = sc.nextInt();

            if (option == 0) {
                return;
            }
            switch (option) {
                case 1 -> {listSeachName(sc);}
                case 2 -> {listhigherValue();}
                case 3 -> {listpricePerProduct();}
                case 4 -> {listsearchCategory(sc);}
                default -> {
                    System.out.println("Choose valid option.");
                }
            }
        }
    }

    static void showMain() {
        System.out.println("+======================================+");
        System.out.println("|           MANAGE PRODUCTS            |");
        System.out.println("+======================================+");
        System.out.println("|  [1] Register product                |");
        System.out.println("|  [2] Product table                   |");
        System.out.println("|  [3] Update product                  |");
        System.out.println("|  [4] Remove product                  |");
        System.out.println("|  [5] Reports                         |");
        System.out.println("|  [0] EXIT                            |");
        System.out.println("+======================================+");
        System.out.print("Choose an option: ");
    }

    static void showReportsMain() {
        System.out.println("+======================================+");
        System.out.println("|             REPORTS MENU             |");
        System.out.println("+======================================+");
        System.out.println("|  [1] Search product by name          |");
        System.out.println("|  [2] Most expensive products table   |");
        System.out.println("|  [3] Sum of product prices           |");
        System.out.println("|  [4] Search product by category      |");
        System.out.println("|  [0] BACK                            |");
        System.out.println("+======================================+");
        System.out.print("Choose an option: ");
    }

    static void registerProduct(Scanner sc) {
        sc.nextLine();
        System.out.println("=-=-=-=-=-=-=-=-=-=- REGISTER =-=-=-=-=-=-=-=-=-=-");
            System.out.println("=-=-=-=-=- Categories =-=-=-=-=-");
            for (CategoryProductEnum cat : CategoryProductEnum.values()) {
                System.out.printf("  [%d] - %-35s \n", cat.getId(), cat.getName());
            }

            System.out.print("Product Name: ");
            String name = sc.nextLine();
            ProductValidator.validateEmptyName(name);
            ProductValidator.validatorString(name);

            System.out.print("Product Category: ");
            CategoryProductEnum cat = CategoryProductEnum.fromId(sc.nextInt());

            System.out.print("Available Quantity: ");
            int availableQuantity = sc.nextInt();

            ProductValidator.validateNegative(availableQuantity);

            System.out.print("Minimum Quantity: ");
            int miniQuantity = sc.nextInt();

            ProductValidator.validateNegative(miniQuantity);

            System.out.print("Product Value: ");
            float value = sc.nextFloat();

            ProductValidator.validateValue(value);
            sc.nextLine();

            System.out.println("=-=-=-=-=- STATUS =-=-=-=-=-");
            for (ProductStatusEnum s : ProductStatusEnum.values()) {
                System.out.printf(" %-35s \n", s.getStatus());
            }

            System.out.print("Status: ");
            ProductStatusEnum status = ProductStatusEnum.valueOf(sc.nextLine().toUpperCase());

            Product product = new Product(name, cat, availableQuantity, miniQuantity, value, status);
            ProductDAO dao = new ProductDAOImpl();
            dao.insert(product);

    }

    static void tableList() {
        System.out.println("=-=-=-=-=-=-=-=-=-=- PRODUCTS TABLE =-=-=-=-=-=-=-=-=-=-");
        ProductDAO dao = new ProductDAOImpl();
        List<Product> products = dao.generalReport();

        System.out.println("+------+----------------------+----------------------+-------------+-----------+------------+--------------+");
        System.out.println("| ID   | Name                 | Category             | Avail. Qty. | Min. Qty. | Price      | Status       |");
        System.out.println("+------+----------------------+----------------------+-------------+-----------+------------+--------------+");

        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println("+------+----------------------+----------------------+-------------+-----------+------------+--------------+");
    }

    static void updateTable(Scanner sc) {
        sc.nextLine();
        System.out.println("=-=-=-=-=-=-=-=-=-=- UPDATE =-=-=-=-=-=-=-=-=-=-");
        System.out.print("ID Product: ");
        int id = sc.nextInt();
        System.out.println("=-=-=-=-=- Categories =-=-=-=-=-");
        for (CategoryProductEnum cat : CategoryProductEnum.values()) {
            System.out.printf("  [%d] - %-35s \n", cat.getId(), cat.getName());
        }

        sc.nextLine();
        System.out.print("Product Name: ");
        String name = sc.nextLine();
        ProductValidator.validateEmptyName(name);
        ProductValidator.validatorString(name);

        System.out.print("Product Category: ");
        CategoryProductEnum cat = CategoryProductEnum.fromId(sc.nextInt());

        System.out.print("Available Quantity: ");
        int availableQuantity = sc.nextInt();

        ProductValidator.validateNegative(availableQuantity);

        System.out.print("Minimum Quantity: ");
        int miniQuantity = sc.nextInt();

        ProductValidator.validateNegative(miniQuantity);

        System.out.print("Product Value: ");
        float value = sc.nextFloat();

        ProductValidator.validateValue(value);
        sc.nextLine();

        System.out.print("=-=-=-=-=- STATUS =-=-=-=-=-");
        for (ProductStatusEnum s : ProductStatusEnum.values()) {
            System.out.printf(" %-35s \n", s.getStatus());
        }

        System.out.print("Status: ");
        ProductStatusEnum status = ProductStatusEnum.valueOf(sc.nextLine().toUpperCase());

        ProductDAO dao = new ProductDAOImpl();
        Product product = dao.findCode(id);
        product.setProductName(name);
        product.setProductCategory(cat);
        product.setAvailableQuantity(availableQuantity);
        product.setMinimumQuantity(miniQuantity);
        product.setProductValue(value);
        product.setProductStatus(status);
        dao.update(id, product);
    }

    static void removeTable(Scanner sc) {
        sc.nextLine();
        System.out.println("=-=-=-=-=-=-=-=-=-=- REMOVE =-=-=-=-=-=-=-=-=-=-");
        System.out.print("ID product: ");
        int id = sc.nextInt();

        ProductDAO dao = new ProductDAOImpl();
        dao.remove(id);
    }

    static void listSeachName(Scanner sc) {
        sc.nextLine();
        System.out.println("=-=-=-=-=-=-=-=-=-=- PRODUCTS NAME TABLE =-=-=-=-=-=-=-=-=-=-");
        System.out.print("Product Name: ");
        String name = sc.nextLine();
        ProductValidator.validateEmptyName(name);
        ProductValidator.validatorString(name);

        System.out.println("+------+----------------------+----------------------+-------------+-----------+------------+--------------+");
        System.out.println("| ID   | Name                 | Category             | Avail. Qty. | Min. Qty. | Price      | Status       |");
        System.out.println("+------+----------------------+----------------------+-------------+-----------+------------+--------------+");

        ProductDAO dao = new ProductDAOImpl();
        List<Product> products = dao.searchName(name);

        for (Product p : products) {
            System.out.println(p.toString());
        }

        System.out.println("+------+----------------------+----------------------+-------------+-----------+------------+--------------+");
    }

    static void listhigherValue() {
        System.out.println("=-=-=-=-=-=-=-=-=-=- PRODUCTS HIGHER VALUE TABLE =-=-=-=-=-=-=-=-=-=-");

        ProductDAO dao = new ProductDAOImpl();
        List<Product> products = dao.higherValue();

        System.out.println("+------+----------------------+------------+");
        System.out.println("| ID   | Name                 | Price      |");
        System.out.println("+------+----------------------+------------+");

        for (Product p : products) {
            System.out.println("|  " + p.getId() + "           |  " + p.getProductName() + "          |  " + p.getProductValue() + "|");
        }

        System.out.println("+------+----------------------+------------+");
    }

    static void listpricePerProduct() {
        System.out.println("=-=-=-=-=-=-=-=-=-=- PRICE PER PRODUCT TABLE =-=-=-=-=-=-=-=-=-=-");

        ProductDAO dao = new ProductDAOImpl();
        List<Product> products = dao.pricePerProduct();

        System.out.println("+------+----------------------+------------+");
        System.out.println("| Name                 | Total             |");
        System.out.println("+------+----------------------+------------+");

        for (Product p : products) {
            System.out.println("|  " + p.getProductName() + "            | " + p.getProductValue() + "        |");
        }

        System.out.println("+------+----------------------+------------+");
    }

    static void listsearchCategory(Scanner sc) {
        sc.nextLine();
        System.out.println("=-=-=-=-=-=-=-=-=-=- PRODUCT CATEGORY TABLE =-=-=-=-=-=-=-=-=-=-");
        System.out.print("Code Category Product: ");
        int cat = sc.nextInt();
        ProductValidator.validateNegative(cat);

        ProductDAO dao = new ProductDAOImpl();
        List<Product> products = dao.searchCategory(CategoryProductEnum.fromId(cat));

        System.out.println("+------+----------------------+----------------------+-------------+-----------+------------+--------------+");
        System.out.println("| ID   | Name                 | Category             | Avail. Qty. | Min. Qty. | Price      | Status       |");
        System.out.println("+------+----------------------+----------------------+-------------+-----------+------------+--------------+");

        for (Product p : products) {
            System.out.println(p);
        }

        System.out.println("+------+----------------------+----------------------+-------------+-----------+------------+--------------+");
    }
}
