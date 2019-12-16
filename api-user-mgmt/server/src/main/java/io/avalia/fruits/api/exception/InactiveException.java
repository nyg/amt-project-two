package io.avalia.fruits.api.exception;

public class InactiveException extends RuntimeException {

    public InactiveException() {
        super("Inactive account.");
    }
}
