package ch.heig.amt.business.server.api.endpoints;

import ch.heig.amt.business.server.api.ArticlesApi;
import ch.heig.amt.business.server.api.model.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-01-06T14:28:12.171Z")

@Controller
public class ArticlesApiController implements ArticlesApi {

    private static final Logger log = LoggerFactory.getLogger(ArticlesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ArticlesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Article>> articlesGet() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Article>>(objectMapper.readValue("[ {  \"price\" : 6.02745618307040320615897144307382404804229736328125,  \"name\" : \"name\",  \"description\" : \"description\",  \"id\" : 0}, {  \"price\" : 6.02745618307040320615897144307382404804229736328125,  \"name\" : \"name\",  \"description\" : \"description\",  \"id\" : 0} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Article>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Article>>(HttpStatus.NOT_IMPLEMENTED);
    }

}