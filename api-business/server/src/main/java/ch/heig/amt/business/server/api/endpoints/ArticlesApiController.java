package ch.heig.amt.business.server.api.endpoints;

import ch.heig.amt.business.server.api.ArticlesApi;
import ch.heig.amt.business.server.api.model.Article;
import ch.heig.amt.business.server.entities.ArticleEntity;
import ch.heig.amt.business.server.entities.FruitEntity;
import ch.heig.amt.business.server.repositories.ArticleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-01-06T14:28:12.171Z")

@Controller
public class ArticlesApiController implements ArticlesApi {


    @Autowired
    ArticleRepository articleRepository;

    private static final Logger log = LoggerFactory.getLogger(ArticlesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ArticlesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Article>> articlesGet() {
       List<Article> articles = new ArrayList<>();
       for (ArticleEntity articleEntity : articleRepository.findAll()){
           articles.add(toArticle(articleEntity));
       }
       return ResponseEntity.ok(articles);
    }

    private Article toArticle(ArticleEntity entity){
        Article article = new Article();
        article.setDescription(entity.getDescription());
        article.setId(entity.getId());
        article.setName(entity.getName());
        article.setPrice(article.getPrice());
    }
}