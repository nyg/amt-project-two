package ch.heig.amt.user.mgmt.server.api.endpoint;

import ch.heig.amt.user.mgmt.api.PublicApi;
import ch.heig.amt.user.mgmt.api.model.Identifiers;
import ch.heig.amt.user.mgmt.api.model.Token;
import ch.heig.amt.user.mgmt.api.model.User;
import ch.heig.amt.user.mgmt.server.api.exception.UserCreationException;
import ch.heig.amt.user.mgmt.server.entity.UserEntity;
import ch.heig.amt.user.mgmt.server.service.AuthenticationService;
import ch.heig.amt.user.mgmt.server.service.ValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import ch.heig.amt.user.mgmt.server.api.exception.AuthenticationException;
import ch.heig.amt.user.mgmt.server.api.exception.InactiveException;
import ch.heig.amt.user.mgmt.server.repository.UserRepository;
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

    @Autowired
    ValidationService validationService;

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    public PublicController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Token> authenticateUser(@ApiParam(value = "", required = true) @Valid @RequestBody Identifiers identifiers) throws AuthenticationException {

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

    public ResponseEntity<Void> createUser(@ApiParam(value = "", required = true) @Valid @RequestBody User user) {

        if (!validationService.email(user.getEmail())) {
            throw new UserCreationException("Incorrect email");
        }

        if (!validationService.password(user.getPassword())) {
            throw new UserCreationException("Incorrect password");
        }

        Optional<UserEntity> currentEntity = userRepository.findById(user.getEmail());
        if (currentEntity.isPresent()) {
            throw new UserCreationException("Email already taken");
        }

        UserEntity entity = new UserEntity(user);

        // hash password
        entity.setPassword(authenticationService.hashPassword(entity.getPassword()));

        userRepository.save(entity);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
