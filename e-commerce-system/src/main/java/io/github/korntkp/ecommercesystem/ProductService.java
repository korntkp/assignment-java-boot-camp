package io.github.korntkp.ecommercesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product findProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new ProductNotFoundException(id);
    }
//    public Product findProductByName(String name) {
//        Optional<Product> product = productRepository.findByName(name);
//        if (product.isPresent()) {
//            return product.get();
//        }
//        throw new ProductNotFoundException(name);
//    }
}
