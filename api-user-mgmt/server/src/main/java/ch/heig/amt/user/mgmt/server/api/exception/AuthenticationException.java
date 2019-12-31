package ch.heig.amt.user.mgmt.server.api.exception;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super("Authentication failed");
    }
}
