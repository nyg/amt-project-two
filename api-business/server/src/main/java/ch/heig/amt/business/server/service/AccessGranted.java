package ch.heig.amt.business.server.service;

import javax.servlet.http.HttpServletRequest;

public interface AccessGranted {

    boolean granted(HttpServletRequest request);
}
