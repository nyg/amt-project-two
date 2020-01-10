package ch.heig.amt.business.server.api.exceptions;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super("Authentication failed");
    }
}
