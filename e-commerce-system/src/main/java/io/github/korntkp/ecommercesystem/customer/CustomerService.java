package io.github.korntkp.ecommercesystem.customer;

import io.github.korntkp.ecommercesystem.errorHandler.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public Customer getCustomerById(int id) {
        Optional<Customer> result = customerRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new CustomerNotFoundException(id);
    }
}
