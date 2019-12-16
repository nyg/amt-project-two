package io.avalia.fruits.service;

public interface AuthenticationService {

    String hashPassword(String password);

    boolean verify(String plainText, String hash);

    String generateToken(String email, boolean admin);
}
