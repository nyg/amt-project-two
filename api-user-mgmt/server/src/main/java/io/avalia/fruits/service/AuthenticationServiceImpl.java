package io.avalia.fruits.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger LOG = Logger.getLogger(AuthenticationServiceImpl.class.getSimpleName());
    private static final MessageDigest DIGEST;

    static {
        try {
            DIGEST = MessageDigest.getInstance("SHA-512");
        }
        catch (NoSuchAlgorithmException e) {
            throw new ExceptionInInitializerError();
        }
    }

    @Override
    public String hashPassword(String password) {
        byte[] hash = DIGEST.digest(password.getBytes(StandardCharsets.UTF_8));
        return String.format("%064x", new BigInteger(1, hash));
    }

    @Override
    public boolean verify(String plain, String hash) {

        if (plain == null || hash == null) {
            return false;
        }

        return hash.equals(hashPassword(plain));
    }

    @Override
    public String generateToken(String email, boolean admin) {

        try {
            // TODO change secret
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withClaim("email", email)
                    .withClaim("admin", admin)
                    .sign(algorithm);
        }
        catch (JWTCreationException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public DecodedJWT verifyToken(String token) {

        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secret")).build();
            return verifier.verify(token);
        }
        catch (JWTVerificationException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }
}
