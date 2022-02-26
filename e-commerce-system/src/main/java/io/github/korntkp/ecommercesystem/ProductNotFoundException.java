package io.github.korntkp.ecommercesystem;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String name) {
        super(name);
    }
}
