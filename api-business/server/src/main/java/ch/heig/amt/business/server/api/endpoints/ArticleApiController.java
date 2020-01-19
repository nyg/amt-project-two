package ch.heig.amt.business.server.api.endpoints;

import ch.heig.amt.business.server.api.ArticleApi;
import ch.heig.amt.business.server.api.exceptions.AdminException;
import ch.heig.amt.business.server.api.exceptions.AuthenticationException;
import ch.heig.amt.business.server.api.model.Article;
import ch.heig.amt.business.server.api.model.OptionalArticle;
import ch.heig.amt.business.server.entities.ArticleEntity;
import ch.heig.amt.business.server.repositories.ArticleRepository;
import ch.heig.amt.business.server.service.AccessGranted;
import ch.heig.amt.business.server.service.AuthenticationService;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-16T15:02:02.960Z")

@Controller
public class ArticleApiController implements ArticleApi {

    private static final Logger log = LoggerFactory.getLogger(ArticleApiController.class);

    private final ObjectMapper objectMapper;
    @Autowired
    AccessGranted accessGranted;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    ArticleRepository articleRepository;

    private final HttpServletRequest request;

    @Autowired
    public ArticleApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Article> articleArticleIDGet(@ApiParam(value = "ID of the article", required = true) @PathVariable("articleID") Long articleID) {
        DecodedJWT token = accessGranted.granted(request);
        if (token != null) {

            Optional<ArticleEntity> currentEntity = articleRepository.findById(articleID);
            ArticleEntity articleEntity = currentEntity.get();
            Article article = toArticle(articleEntity);

            return new ResponseEntity<>(article, HttpStatus.OK);
        }
        throw new AuthenticationException();
    }
    public ResponseEntity<Void> articleArticleIDDelete(@ApiParam(value = "ID of the article",required=true) @PathVariable("articleID") Long articleID) {
        DecodedJWT token = accessGranted.granted(request);
        if (token != null && token.getClaim("admin").asBoolean()) {

            Optional<ArticleEntity> currentEntity = articleRepository.findById(articleID);
            ArticleEntity articleEntity = currentEntity.get();
            articleRepository.delete(articleEntity);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new AdminException();
    }

    public ResponseEntity<Article> articlePost(@ApiParam(value = "", required = true) @Valid @RequestBody Article article) {

        DecodedJWT token = accessGranted.granted(request);
        if (token != null && token.getClaim("admin").asBoolean()) {

            boolean admin = token.getClaim("admin").asBoolean();
            //TODO : if admin not true launch exception

            ArticleEntity newArticleEntity = toArticleEntity(article);
            articleRepository.save(newArticleEntity);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newArticleEntity.getId()).toUri();

            return ResponseEntity.created(location).build();
        }

        throw new AdminException();
    }

    public ResponseEntity<Article> articlePut(@ApiParam(value = "", required = true) @Valid @RequestBody OptionalArticle article) {

        DecodedJWT token = accessGranted.granted(request);
        if (token != null && token.getClaim("admin").asBoolean()) {

            boolean admin = token.getClaim("admin").asBoolean();
            //TODO : if admin not true launch exception


            Optional<ArticleEntity> currentEntity = articleRepository.findById(article.getId());
            if (currentEntity.isPresent()) {
                ArticleEntity articleEntity = currentEntity.get();
                articleEntity.setPrice(article.getPrice());
                articleEntity.setName(article.getName());
                articleEntity.setDescription(article.getDescription());

                ArticleEntity savedEntity = articleRepository.save(articleEntity);
                Article savedArticle = new Article()
                        .price(savedEntity.getPrice())
                        .description(savedEntity.getDescription())
                        .name(savedEntity.getName());
                return new ResponseEntity<>(savedArticle, HttpStatus.OK);
            }
        }
        throw new AdminException();
    }

    private ArticleEntity toArticleEntity(Article article) {
        ArticleEntity entity = new ArticleEntity();
        entity.setDescription(article.getDescription());
        if(article.getId() != null) {
            entity.setId(article.getId());
        }
        entity.setName(article.getName());
        entity.setPrice(article.getPrice());
        return entity;
    }
    private Article toArticle(ArticleEntity entity){
        Article article = new Article();
        article.setDescription(entity.getDescription());
        article.setId(entity.getId());
        article.setName(entity.getName());
        article.setPrice(article.getPrice());
        return article;
    }
}
