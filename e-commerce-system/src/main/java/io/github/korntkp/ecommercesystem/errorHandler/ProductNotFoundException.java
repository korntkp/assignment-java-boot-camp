package io.github.korntkp.ecommercesystem.errorHandler;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id) {
        super(id + "");
    }
}
