package io.avalia.fruits.api.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.avalia.fruits.api.ArticleApi;
import io.avalia.fruits.api.model.Article;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-16T15:02:02.960Z")

@Controller
public class ArticleApiController implements ArticleApi {

    private static final Logger log = LoggerFactory.getLogger(ArticleApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ArticleApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> articleDelete() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Article> articlePost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Article article) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Article>(objectMapper.readValue("{  \"price\" : 6.02745618307040320615897144307382404804229736328125,  \"name\" : \"name\",  \"description\" : \"description\",  \"id\" : 0}", Article.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Article>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Article>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Article> articlePut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Article article) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Article>(objectMapper.readValue("{  \"price\" : 6.02745618307040320615897144307382404804229736328125,  \"name\" : \"name\",  \"description\" : \"description\",  \"id\" : 0}", Article.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Article>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Article>(HttpStatus.NOT_IMPLEMENTED);
    }

}
