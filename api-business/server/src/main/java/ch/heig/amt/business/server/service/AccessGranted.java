package ch.heig.amt.business.server.service;

import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;

public interface AccessGranted {

    DecodedJWT granted(HttpServletRequest request);
}
