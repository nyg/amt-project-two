package ch.heig.amt.business.server.api.endpoints;

import ch.heig.amt.business.server.api.CartApi;
import ch.heig.amt.business.server.api.exceptions.AuthenticationException;
import ch.heig.amt.business.server.api.model.Article;
import ch.heig.amt.business.server.entities.CartEntity;
import ch.heig.amt.business.server.entities.CustomerEntity;
import ch.heig.amt.business.server.repositories.CartRepository;
import ch.heig.amt.business.server.repositories.CustomerRepository;
import ch.heig.amt.business.server.service.AccessGranted;
import ch.heig.amt.business.server.service.AuthenticationService;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-01-06T15:11:07.800Z")

@Controller
public class CartApiController implements CartApi {

    @Autowired
    AccessGranted accessGranted;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartRepository cartRepository;

    private static final Logger log = LoggerFactory.getLogger(CartApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CartApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    public ResponseEntity<Void> cartArticleIDDelete(@ApiParam(value = "ID of the article",required=true) @PathVariable("articleID") Integer articleID) {
        DecodedJWT token = accessGranted.granted(request);
        if(token != null){

            String email = token.getClaim("email").asString();
            Optional<CustomerEntity> currentEntity = customerRepository.findById(email);
            CustomerEntity customer = currentEntity.get();
            Optional<CartEntity> cart = cartRepository.findById(customer.getEmail());

            //get current list
            CartEntity cartEntity = cart.get();
            ArrayList<Article> newArticleList = new ArrayList<Article>();

            for(Article a : cartEntity.getListArticle()){
                if (!a.getId().equals(articleID)){
                    newArticleList.add(a);
                }
            }
            cartEntity.setListArticle(newArticleList);
            cartRepository.save(cartEntity);

            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return null;
    }

    public ResponseEntity<Void> cartDelete(){
        DecodedJWT token = accessGranted.granted(request);
        if(token != null){

            String email = token.getClaim("email").asString();
            Optional<CustomerEntity> currentEntity = customerRepository.findById(email);
            CustomerEntity customer = currentEntity.get();
            Optional<CartEntity> cart = cartRepository.findById(customer.getEmail());

            //get current list
            CartEntity cartEntity = cart.get();
            cartEntity.setListArticle(new ArrayList<Article>());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return null;
    }

    public ResponseEntity<List<Article>> cartGet() {
        DecodedJWT token = accessGranted.granted(request);
        if(token != null){

            String email = token.getClaim("email").asString();
            Optional<CustomerEntity> currentEntity = customerRepository.findById(email);
            CustomerEntity customer = currentEntity.get();
            Optional<CartEntity> cart = cartRepository.findById(customer.getEmail());

            if(cart.isPresent()){
                CartEntity cartEntity = cart.get();
                if(!(cartEntity.getListArticle() == null)){
                    List<Article> articleList = cart.get().getListArticle();
                        return ResponseEntity.ok(articleList);
                }
            }
            else return ResponseEntity.ok(Collections.emptyList());
        }
        return null;
    }

    public ResponseEntity<List<Article>> cartPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Article article) {
        DecodedJWT token = accessGranted.granted(request);
        if(token != null){

            String email = token.getClaim("email").asString();
            Optional<CustomerEntity> currentEntity = customerRepository.findById(email);
            CustomerEntity customer = currentEntity.get();
            Optional<CartEntity> cart = cartRepository.findById(customer.getEmail());

            ArrayList<Article> articleArrayList = null;
            CartEntity cartEntity = null;
            //get current list
            if(cart.isPresent()) {
                cartEntity = cart.get();
                articleArrayList = cartEntity.getListArticle();
                if (articleArrayList == null) {
                    articleArrayList = new ArrayList<Article>();
                }
            }
            else {
                articleArrayList = new ArrayList<Article>();
                cartEntity = new CartEntity();
                cartEntity.setCustomerId(email);
            }
            //update current list

            articleArrayList.add(article);
            cartEntity.setListArticle(articleArrayList);
            cartRepository.save(cartEntity);

            List<Article> articleList = cartEntity.getListArticle();
            return ResponseEntity.ok(articleList);


        }

        return null;
    }

}