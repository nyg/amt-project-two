package ch.heig.amt.business.server.api.endpoints;

import ch.heig.amt.business.server.api.exceptions.AuthenticationException;
import ch.heig.amt.business.server.api.exceptions.CustomerNotFoundException;
import ch.heig.amt.business.server.api.model.Customer;
import ch.heig.amt.business.server.api.model.OptionalCustomer;
import ch.heig.amt.business.server.entities.CustomerEntity;
import ch.heig.amt.business.server.repositories.CustomerRepository;
import com.auth0.jwt.interfaces.DecodedJWT;
import ch.heig.amt.business.server.api.CustomerApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import ch.heig.amt.business.server.service.AccessGranted;
import ch.heig.amt.business.server.service.AuthenticationService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-01-06T14:28:12.171Z")

@Controller
public class CustomerApiController implements CustomerApi {

    private static final Logger log = LoggerFactory.getLogger(CustomerApiController.class);
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccessGranted accessGranted;
    @Autowired
    AuthenticationService authenticationService;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CustomerApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Customer> customerGet() {
        /*
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Customer>>(objectMapper.readValue("[ {  \"firstName\" : \"firstName\",  \"lastName\" : \"lastName\",  \"address\" : {    \"ZIP\" : 6,    \"number\" : 0,    \"country\" : \"country\",    \"city\" : \"city\",    \"street\" : \"street\"  },  \"email\" : \"email\"}, {  \"firstName\" : \"firstName\",  \"lastName\" : \"lastName\",  \"address\" : {    \"ZIP\" : 6,    \"number\" : 0,    \"country\" : \"country\",    \"city\" : \"city\",    \"street\" : \"street\"  },  \"email\" : \"email\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }*/
        DecodedJWT token = accessGranted.granted(request);
        if(token != null){

            String email = token.getClaim("email").asString();
            Optional<CustomerEntity> currentEntity = customerRepository.findById(email);
            CustomerEntity customer = currentEntity.get();
            Customer savedCustomer = new Customer()
                    .address(customer.getAddress())
                    .email(customer.getEmail())
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .address(customer.getAddress());
            return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
        }
        return null;
    }

    public ResponseEntity<Customer> customerPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody OptionalCustomer customer) {
        /*String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Customer>(objectMapper.readValue("{  \"firstName\" : \"firstName\",  \"lastName\" : \"lastName\",  \"address\" : {    \"ZIP\" : 6,    \"number\" : 0,    \"country\" : \"country\",    \"city\" : \"city\",    \"street\" : \"street\"  },  \"email\" : \"email\"}", Customer.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Customer>(HttpStatus.NOT_IMPLEMENTED);
        */
        DecodedJWT token = accessGranted.granted(request);
        if(token != null){  //si le customer est bien identifié

            Optional<CustomerEntity> currentEntity = customerRepository.findById(customer.getEmail());
            if(currentEntity.isPresent()){
                CustomerEntity customerEntity = currentEntity.get();
                customerEntity.setLastName(customer.getLastName());
                customerEntity.setFirstName(customer.getFirstName());
                customerEntity.setAddress(customer.getAddress());

                CustomerEntity savedEntity = customerRepository.save(customerEntity);
                Customer savedCustomer = new Customer()
                        .email(savedEntity.getEmail())
                        .firstName(savedEntity.getFirstName())
                        .lastName(savedEntity.getLastName())
                        .address(savedEntity.getAddress());

                return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
            }
            else{
                throw new CustomerNotFoundException();
            }
        }
        return null;
    }


}