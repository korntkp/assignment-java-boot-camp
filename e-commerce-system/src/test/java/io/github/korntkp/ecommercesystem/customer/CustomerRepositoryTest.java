package io.github.korntkp.ecommercesystem.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("findById: ส่ง id=2 แล้วจะต้องได้รับ Customer ที่มีคำว่า Test22")
    void case01() {
        // Arrange
        Customer customer1 = new Customer(1, "Testing");
        Customer customer2 = new Customer(2, "Test22");
        Customer customer3 = new Customer(3, "Testing3");
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);

        // Act
        Optional<Customer> result = customerRepository.findById(2);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Test22", result.get().getFirstName());
    }

    @Test
    @DisplayName("findById: ส่ง id=99 แล้วจะต้องไม่พบ Customer")
    void case02() {
        // Arrange
        Customer customer1 = new Customer(1, "Testing");
        Customer customer2 = new Customer(2, "Test22");
        Customer customer3 = new Customer(3, "Testing3");
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);

        // Act
        Optional<Customer> result = customerRepository.findById(99);

        // Assert
        assertTrue(result.isEmpty());
    }
}