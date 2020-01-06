package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.CustomerApi;
import io.avalia.fruits.api.exceptions.CustomerNotFoundException;
import io.avalia.fruits.api.model.Customer;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-16T15:02:02.960Z")

@Controller
public class CustomerApiController implements CustomerApi {

    @Autowired
    CustomerRepository customerRepository;

    private static final Logger log = LoggerFactory.getLogger(CustomerApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CustomerApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Customer> customerPut(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Customer customer) {

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
