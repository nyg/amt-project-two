package io.avalia.fruits.api.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.avalia.fruits.api.PublicApi;
import io.avalia.fruits.api.exception.AuthenticationException;
import io.avalia.fruits.api.exception.InactiveException;
import io.avalia.fruits.api.model.Identifiers;
import io.avalia.fruits.api.model.Token;
import io.avalia.fruits.entity.UserEntity;
import io.avalia.fruits.repository.UserRepository;
import io.avalia.fruits.service.AuthenticationService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.logging.Logger;

@Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-15T00:41:05.824Z")
@Controller
public class PublicController implements PublicApi {

    private static final Logger LOG = Logger.getLogger(PublicController.class.getSimpleName());

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    public PublicController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Token> publicAuthenticatePost(@ApiParam(value = "", required = true) @Valid @RequestBody Identifiers identifiers) throws AuthenticationException {

        @NotNull String email = identifiers.getEmail();
        @NotNull String password = identifiers.getPassword();

        // check account exists and password is ok
        Optional<UserEntity> entity = userRepository.findById(email);
        if (entity.isEmpty() || !authenticationService.verify(password, entity.get().getPassword())) {
            throw new AuthenticationException();
        }

        // check user is active
        UserEntity user = entity.get();
        if (!user.isActive()) {
            throw new InactiveException();
        }

        // generate and return JWT token
        Token token = new Token();
        token.setToken(authenticationService.generateToken(user.getEmail(), user.isAdmin()));
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
