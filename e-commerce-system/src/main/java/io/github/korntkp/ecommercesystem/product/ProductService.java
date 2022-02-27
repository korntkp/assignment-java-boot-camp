package io.github.korntkp.ecommercesystem.product;

import io.github.korntkp.ecommercesystem.errorHandler.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new ProductNotFoundException(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findProductsByName(String name) {
        return productRepository.findAllByName(name);
    }
}
