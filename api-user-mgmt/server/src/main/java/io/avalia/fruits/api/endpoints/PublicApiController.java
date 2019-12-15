package io.avalia.fruits.api.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.avalia.fruits.api.PublicApi;
import io.avalia.fruits.api.model.Identifiers;
import io.avalia.fruits.api.model.Token;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-15T00:41:05.824Z")

@Controller
public class PublicApiController implements PublicApi {

    private static final Logger log = LoggerFactory.getLogger(PublicApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PublicApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Token> publicAuthenticatePost(@ApiParam(value = "", required = true) @Valid @RequestBody Identifiers identifiers) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Token>(objectMapper.readValue("{  \"value\" : \"value\"}", Token.class), HttpStatus.NOT_IMPLEMENTED);
            }
            catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Token>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Token>(HttpStatus.NOT_IMPLEMENTED);
    }
}
