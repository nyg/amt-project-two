package ch.heig.amt.user.mgmt.server.api.exception;

public class InactiveException extends RuntimeException {

    public InactiveException() {
        super("Inactive account.");
    }
}
