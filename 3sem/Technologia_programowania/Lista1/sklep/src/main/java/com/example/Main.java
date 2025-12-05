package com.example;

import java.util.Scanner;

/**
 * The Main class provides an interface for interacting
 * with the Warehouse class.
 * Users can add, delete, view products, or exit the program.
 */
public final class Main {

    /** Option to add a product. */
    private static final int ADD_PRODUCT = 1;

    /** Option to delete a product. */
    private static final int DELETE_PRODUCT = 2;

    /** Option to see all products. */
    private static final int SEE_PRODUCTS = 3;

    /** Option to exit the program. */
    private static final int EXIT = 4;

    /** Private constructor to prevent instantiation. */
    private Main() {
        // Prevent instantiation
    }
    /**
     * The main method serves as the entry point of the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(final String[] args) {

        Scanner scanner = new Scanner(System.in);
        Warehouse warehouse = new Warehouse();
        System.out.println("Hello!");

        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("1. Add a product");
            System.out.println("2. Delete a product");
            System.out.println("3. See all of products");
            System.out.println("4. Exit");
            int value = scanner.nextInt();
            scanner.nextLine();

            switch (value) {
                case ADD_PRODUCT:
                    System.out.println("Product name:");
                    String name = scanner.nextLine();
                    System.out.println("Product price:");
                    String price = scanner.nextLine();
                    warehouse.addProduct(
                        new Product(name, Double.parseDouble(price)));
                    break;
                case DELETE_PRODUCT:
                    System.out.println("Product name:");
                    name = scanner.nextLine();
                    warehouse.deleteProduct(name);
                    break;
                case SEE_PRODUCTS:
                    System.out.println(warehouse);
                    break;
                case EXIT:
                    break;
                default:
                    System.err.println("No such action!");
                    break;
            }

            if (value == EXIT) {
                break;
            }
        }
        scanner.close();
    }
}
