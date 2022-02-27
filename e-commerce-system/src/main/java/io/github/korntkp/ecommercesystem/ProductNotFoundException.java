package io.github.korntkp.ecommercesystem;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id) {
        super(id + "");
    }
}
