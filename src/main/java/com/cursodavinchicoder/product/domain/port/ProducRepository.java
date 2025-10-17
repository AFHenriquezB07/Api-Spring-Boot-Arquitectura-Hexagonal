package com.cursodavinchicoder.product.domain.port;

import com.cursodavinchicoder.product.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProducRepository {

    void upsert(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    void deleteById(Long id);
}
