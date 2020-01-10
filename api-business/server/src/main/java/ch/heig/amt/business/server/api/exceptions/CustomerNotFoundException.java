package ch.heig.amt.business.server.api.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException() {
        super("User not found");
    }
}
