package ch.heig.amt.user.mgmt.server.service;

import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    private static final String RE_EMAIL = "[a-zA-Z0-9._-]+@(\\w+\\.)+\\w{2,63}";
    private static final int PWD_LENGTH = 8;

    @Override
    public boolean email(String email) {
        return notNull(email) && notBlank(email) && regex(email, RE_EMAIL);
    }

    @Override
    public boolean password(String password) {
        return notNull(password) && notBlank(password) && minLength(password, PWD_LENGTH);
    }

    @Override
    public boolean firstName(String name) {
        return notNull(name) && notBlank(name);
    }

    @Override
    public boolean lastName(String name) {
        return notNull(name) && notBlank(name);
    }

    /* Private methods */

    private boolean notNull(Object o) {
        return o != null;
    }

    private boolean notBlank(String s) {
        return !s.isBlank();
    }

    private boolean regex(String s, String pattern) {
        return s.matches(pattern);
    }

    private boolean minLength(String s, int length) {
        return s.length() >= length;
    }
}
