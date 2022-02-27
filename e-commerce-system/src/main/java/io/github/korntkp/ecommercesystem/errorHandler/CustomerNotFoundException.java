package io.github.korntkp.ecommercesystem.errorHandler;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(int id) {
        super(id + "");
    }
}
