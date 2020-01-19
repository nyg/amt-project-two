package ch.heig.amt.business.server.api.endpoints;

import ch.heig.amt.business.server.api.exceptions.AuthenticationException;
import ch.heig.amt.business.server.api.exceptions.CustomerNotFoundException;
import ch.heig.amt.business.server.api.model.Customer;
import ch.heig.amt.business.server.api.model.OptionalCustomer;
import ch.heig.amt.business.server.entities.CustomerEntity;
import ch.heig.amt.business.server.repositories.CustomerRepository;
import ch.heig.amt.business.server.service.NewUserService;
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

    @Autowired
    NewUserService newUserService;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CustomerApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Customer> customerGet() {

        DecodedJWT token = accessGranted.granted(request);
        if (token != null) {

            String email = token.getClaim("email").asString();

            Optional<CustomerEntity> currentEntity = customerRepository.findById(email);
            //if user doesn't exist in DB we insert him
            if(!currentEntity.isPresent()){
                newUserService.CreateNewUser(email);
                currentEntity = customerRepository.findById(email);
            }

            CustomerEntity customer = currentEntity.get();
            Customer savedCustomer = new Customer()
                    .address(customer.getAddress())
                    .email(customer.getEmail())
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName());
            return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
        }
        throw new AuthenticationException();
    }

    public ResponseEntity<Customer> customerPut(@ApiParam(value = "", required = true) @Valid @RequestBody OptionalCustomer customer) {

        DecodedJWT token = accessGranted.granted(request);
        if (token != null) {  //si le customer est bien identifié

            Optional<CustomerEntity> currentEntity = customerRepository.findById(customer.getEmail());


            if (currentEntity.isPresent()) {
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
            else {
                throw new CustomerNotFoundException();
            }
        }
        throw new AuthenticationException();
    }
}