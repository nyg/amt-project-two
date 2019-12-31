package ch.heig.amt.user.mgmt.server.service;

public interface ValidationService {

    boolean email(String email);

    boolean password(String password);

    boolean firstName(String name);

    boolean lastName(String name);
}
