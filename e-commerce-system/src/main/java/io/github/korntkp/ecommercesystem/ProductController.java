package io.github.korntkp.ecommercesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/product/{id}")
    public Product getProductByName(@PathVariable int id) {
        return productService.findProductById(id);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

//    @GetMapping("/products")
//    public List<Product> getProductByName() {
//        return productService.findAll();
//    }

//    @GetMapping("/products")
//    public Product getProducts(@RequestParam String name) {
//        return productService.findProductByName(name);
//    }
}
