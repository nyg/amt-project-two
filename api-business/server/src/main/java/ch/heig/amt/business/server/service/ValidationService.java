package ch.heig.amt.business.server.service;

public interface ValidationService {

    boolean email(String email);

    boolean password(String password);

    boolean firstName(String name);

    boolean lastName(String name);
}
