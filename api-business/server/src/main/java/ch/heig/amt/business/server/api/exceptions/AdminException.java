package ch.heig.amt.business.server.api.exceptions;

public class AdminException extends RuntimeException {

    public AdminException() {
        super("You don't have admin rights");
    }
}
