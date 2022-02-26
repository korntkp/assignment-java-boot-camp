package io.github.korntkp.ecommercesystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Optional<Product> findByName(String name);

    @Query(
        value = "SELECT * FROM product p WHERE p.name LIKE CONCAT('%',?1,'%')",
        nativeQuery = true)
    public List<Product> findAllByName(String name);
}
