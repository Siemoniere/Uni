package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * The Warehouse class manages a collection of products.
 * It provides methods to add and remove products from the warehouse.
 */
public final class Warehouse {

    /** The list of products stored in the warehouse. */
    private final List<Product> products;

    /**
     * Constructs a new Warehouse with an empty list of products.
     */
    public Warehouse() {
        this.products = new ArrayList<>();
    }

    /**
     * Adds a product to the warehouse.
     *
     * @param product the product to be added
     */
    public void addProduct(final Product product) {
        products.add(product);
    }

    /**
     * Removes a product from the warehouse by name.
     *
     * @param name the name of the product to be removed
     */
    public void deleteProduct(final String name) {
        products.removeIf(product -> product.getName().equals(name));
    }

    /**
     * Returns a string representation of the products in the warehouse.
     *
     * @return a formatted string listing the products and their prices
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Products in Warehouse:\n");
        for (Product product : products) {
            result.append("Name: ").append(product.getName())
                  .append(", Price: ").append(product.getPrice()).append("\n");
        }
        return result.toString();
    }
}
