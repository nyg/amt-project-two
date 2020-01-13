package ch.heig.amt.user.mgmt.server.api.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("Email already taken");
    }
}
