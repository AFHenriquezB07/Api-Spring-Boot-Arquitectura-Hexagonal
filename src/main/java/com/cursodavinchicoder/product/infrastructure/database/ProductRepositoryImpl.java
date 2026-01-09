package com.cursodavinchicoder.product.infrastructure.database;

import com.cursodavinchicoder.product.domain.entity.Product;
import com.cursodavinchicoder.product.domain.port.ProductRepository;
import com.cursodavinchicoder.product.infrastructure.database.entity.ProductEntity;
import com.cursodavinchicoder.product.infrastructure.database.mapper.ProductEntityMapper;
import com.cursodavinchicoder.product.infrastructure.database.repository.QueryProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    private final QueryProductRepository repository;

    private final ProductEntityMapper productEntityMapper;


    @Override
    public Product upsert(Product product) {
        ProductEntity productEntity = productEntityMapper.toProductEntity(product);
        ProductEntity save = repository.save(productEntity);
        return productEntityMapper.toProduct(save);
    }

    @Cacheable(value = "products", key = "#id") // Se utiliza para guardar la informacion en cache
    @Override
    public Optional<Product> findById(Long id) {
        log.info("Finding product by id {}", id);
        return repository.findById(id).map(productEntityMapper::toProduct);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(productEntityMapper::toProduct).toList();
    }

    @CacheEvict(value = "products", key = "#id")
    // Se utiliza para eliminar la informacion en cache cuando se elimine se producto y no quede ahì guardada
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
