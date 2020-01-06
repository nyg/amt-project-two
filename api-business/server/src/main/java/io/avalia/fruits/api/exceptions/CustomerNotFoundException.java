package io.avalia.fruits.api.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException() {
        super("User not found");
    }
}
