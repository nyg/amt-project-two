package ch.heig.amt.business.server.service;


import ch.heig.amt.business.server.api.exceptions.AuthenticationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class AccessGrantedImpl implements  AccessGranted{
    @Autowired
    AuthenticationService authenticationService;

    public boolean granted(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.matches("Bearer .*")) {
            throw new AuthenticationException();
        }

        String tokenValue = authorization.split(" ")[1];
        DecodedJWT token = authenticationService.verifyToken(tokenValue);
        if (token == null) {
            throw new AuthenticationException();
        }
        return true;
    }
}
