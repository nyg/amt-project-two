package ch.heig.amt.business.server.service;

import ch.heig.amt.business.server.api.model.Customer;
import ch.heig.amt.business.server.entities.CustomerEntity;
import ch.heig.amt.business.server.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NewUserServiceImpl implements NewUserService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void CreateNewUser(String email){
        CustomerEntity newCustomer = new CustomerEntity();
        newCustomer.setEmail(email);

        customerRepository.save(newCustomer);

    }
}
