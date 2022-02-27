package io.github.korntkp.ecommercesystem.customer;

import io.github.korntkp.ecommercesystem.errorHandler.CustomerNotFoundException;
import io.github.korntkp.ecommercesystem.errorHandler.ProductNotFoundException;
import io.github.korntkp.ecommercesystem.product.Product;
import io.github.korntkp.ecommercesystem.product.ProductRepository;
import io.github.korntkp.ecommercesystem.product.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("findCustomerById: ส่ง id = 1 แล้วจะต้องได้รับ Customer ที่ชื่อ Testing")
    void case01() {
        // Arrange
        Customer customer = new Customer(1, "Testing");
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        // Act
        CustomerService customerService = new CustomerService();
        customerService.setCustomerRepository(customerRepository);
        Customer result = customerService.getCustomerById(1); // id=2 -> Strict stubbing argument mismatch.

        // Assert
        assertEquals("Testing", result.getFirstName());
    }

    @Test
    @DisplayName("findCustomerById: ส่ง id = 99 แล้วจะต้องได้รับ CustomerNotFoundException 99")
    void case02() {
        // Arrange
        when(customerRepository.findById(99)).thenReturn(Optional.empty());

        // Act
        CustomerService customerService = new CustomerService();
        customerService.setCustomerRepository(customerRepository);
        Exception exception = assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(99));

        // Assert, verify
        String expectedMessage = "99";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}