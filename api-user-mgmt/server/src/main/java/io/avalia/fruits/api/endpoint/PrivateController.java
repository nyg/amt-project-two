package io.avalia.fruits.api.endpoint;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.avalia.fruits.api.PrivateApi;
import io.avalia.fruits.api.exception.AuthenticationException;
import io.avalia.fruits.api.exception.UserNotFoundException;
import io.avalia.fruits.api.model.OptionalUser;
import io.avalia.fruits.api.model.User;
import io.avalia.fruits.entity.UserEntity;
import io.avalia.fruits.repository.UserRepository;
import io.avalia.fruits.service.AuthenticationService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-15T00:41:05.824Z")
@Controller
public class PrivateController implements PrivateApi {

    private static final Logger log = LoggerFactory.getLogger(PrivateController.class);

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserRepository userRepository;

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PrivateController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "", required = true) @Valid @RequestBody OptionalUser user) {

        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.matches("Bearer .*")) {
            throw new AuthenticationException();
        }

        String tokenValue = authorization.split(" ")[1];
        DecodedJWT token = authenticationService.verifyToken(tokenValue);
        if (token == null) {
            throw new AuthenticationException();
        }

        String email = token.getClaim("email").asString();
        boolean admin = token.getClaim("admin").asBoolean();

        // admin can modify other users
        if (admin && user.getEmail() != null) {
            email = user.getEmail();
        }

        Optional<UserEntity> entity = userRepository.findById(email);
        if (entity.isEmpty()) {
            throw new UserNotFoundException();
        }

        UserEntity userEntity = entity.get();
        userEntity.setFirstNameIfNotNull(user.getFirstName());
        userEntity.setLastNameIfNotNull(user.getLastName());
        userEntity.setActiveIfNotNull(user.getActive());
        userEntity.setAdminIfNotNull(user.getAdmin());

        if (user.getPassword() != null) {
            userEntity.setPassword(authenticationService.hashPassword(user.getPassword()));
        }

        userRepository.save(userEntity);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
