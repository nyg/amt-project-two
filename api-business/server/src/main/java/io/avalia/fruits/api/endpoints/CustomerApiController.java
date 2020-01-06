package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.CustomerApi;
import io.avalia.fruits.api.exceptions.CustomerNotFoundException;
import io.avalia.fruits.api.model.Customer;
import io.avalia.fruits.api.model.OptionalCustomer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.avalia.fruits.entities.CustomerEntity;
import io.avalia.fruits.repositories.CustomerRepository;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-01-06T14:28:12.171Z")

@Controller
public class CustomerApiController implements CustomerApi {

    private static final Logger log = LoggerFactory.getLogger(CustomerApiController.class);
    @Autowired
    CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CustomerApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Customer>> customerGet() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Customer>>(objectMapper.readValue("[ {  \"firstName\" : \"firstName\",  \"lastName\" : \"lastName\",  \"address\" : {    \"ZIP\" : 6,    \"number\" : 0,    \"country\" : \"country\",    \"city\" : \"city\",    \"street\" : \"street\"  },  \"email\" : \"email\"}, {  \"firstName\" : \"firstName\",  \"lastName\" : \"lastName\",  \"address\" : {    \"ZIP\" : 6,    \"number\" : 0,    \"country\" : \"country\",    \"city\" : \"city\",    \"street\" : \"street\"  },  \"email\" : \"email\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Customer>>(HttpStatus.NOT_IMPLEMENTED);
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
        Optional<CustomerEntity> currentEntity = customerRepository.findById(customer.getEmail());
        if(currentEntity.isPresent()){
            CustomerEntity customerEntity = currentEntity.get();
            customerEntity.setLastName(customer.getLastName());
        }
        else{
            throw new CustomerNotFoundException();
        }
        return null;
    }


}