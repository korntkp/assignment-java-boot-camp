package io.github.korntkp.ecommercesystem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Get Product List: ไม่ส่ง name แล้วจะต้องได้รับ List ที่มีทุก product")
    void case01() {
        // Act
        Product[] resultList = testRestTemplate.getForObject("/products", Product[].class);
        // Assert, verify
        assertEquals(3, resultList.length);
    }

    @Test
    @DisplayName("Get Product List: ส่ง name = Sam แล้วจะต้องได้รับ List ที่มี product name เป็น Samsung S9")
    void case02() {
        // Act
        Product[] resultList = testRestTemplate.getForObject("/products/Sam", Product[].class);
        // Assert, verify
        assertEquals(2, resultList.length);
        assertEquals("Samsung S9", resultList[0].getName());
    }

    @Test
    @DisplayName("Get Product List: ส่ง name = Nokia แล้วจะต้องได้รับ array เปล่า")
    void case03() {
        // Act
        Product[] resultList = testRestTemplate.getForObject("/products/Nokia", Product[].class);
        // Assert, verify
        assertEquals(0, resultList.length);
    }

    @Test
    @DisplayName("Get Product Detail: ส่ง Id = 2 แล้วจะต้องได้รับ Apple Pencil")
    void case04() {
        // Act
        Product result = testRestTemplate.getForObject("/product/2", Product.class);
        // Assert, verify
        assertEquals("Apple Pencil", result.getName());
    }

    @Test
    @DisplayName("Get Product Detail: ส่ง Id = 99 แล้วจะต้องได้รับ Error not found")
    void case05() {
        // Act
        ErrorResponse result = testRestTemplate.getForObject("/product/99", ErrorResponse.class);
        // Assert, verify
        assertEquals(404, result.getCode());
        assertEquals("Product=99 not found", result.getMessage());
    }
}
