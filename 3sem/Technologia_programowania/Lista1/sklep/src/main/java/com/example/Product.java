package com.example;

/**
 * The Product class represents a product with a name and a price.
 * It is designed to be immutable once created.
 */
public final class Product {

    /** The price of the product. */
    private final double price;

    /** The name of the product. */
    private final String name;

    /**
     * Constructs a new Product with the specified name and price.
     *
     * @param productname the name of the product
     * @param productprice the price of the product
     */
    public Product(final String productname, final double productprice) {
        this.name = productname;
        this.price = productprice;
    }

    /**
     * Returns the name of the product.
     *
     * @return the product's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the product.
     *
     * @return the product's price
     */
    public double getPrice() {
        return price;
    }
}
