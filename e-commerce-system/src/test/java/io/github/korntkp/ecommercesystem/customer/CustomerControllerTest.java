package io.github.korntkp.ecommercesystem.customer;

import io.github.korntkp.ecommercesystem.errorHandler.ErrorResponse;
import io.github.korntkp.ecommercesystem.product.Product;
import io.github.korntkp.ecommercesystem.product.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Get Customer Detail: ส่ง Id = 1 แล้วจะต้องได้รับ customer ชื่อ Testing")
    void case01() {
        // Arrange
        Customer customer = new Customer(1, "Testing");
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        // Act
        Customer result = testRestTemplate.getForObject("/customer/1", Customer.class);

        // Assert, verify
        assertEquals("Testing", result.getFirstName());
    }

    @Test
    @DisplayName("Get Customer Detail: ส่ง Id = 99 แล้วจะต้องได้รับ Error not found")
    void case02() {
        // Arrange

        // Act
        ErrorResponse result = testRestTemplate.getForObject("/customer/99", ErrorResponse.class);

        // Assert, verify
        assertEquals(404, result.getCode());
        assertEquals("Customer=99 not found", result.getMessage());
    }
}
