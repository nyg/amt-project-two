package ch.heig.amt.user.mgmt.server.service;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface AuthenticationService {

    String hashPassword(String password);

    boolean verify(String plainText, String hash);

    String generateToken(String email, boolean admin);

    DecodedJWT verifyToken(String token);
}
