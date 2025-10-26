package com.cursodavinchicoder.product.infrastructure.database;

import com.cursodavinchicoder.product.domain.entity.Product;
import com.cursodavinchicoder.product.domain.port.ProductRepository;
import com.cursodavinchicoder.product.infrastructure.database.entity.ProductEntity;
import com.cursodavinchicoder.product.infrastructure.database.mapper.ProductEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    private final List<ProductEntity> products = new ArrayList<>();

    private final ProductEntityMapper productEntityMapper;


    @Override
    public void upsert(Product product) {
        ProductEntity productEntity = productEntityMapper.toProductEntity(product);
        products.removeIf(p -> p.getId().equals(productEntity.getId()));
        products.add(productEntity);
    }

    @Cacheable(value = "products", key = "#id") // Se utiliza para guardar la informacion en cache
    @Override
    public Optional<Product> findById(Long id) {
        log.info("Finding product by id {}", id);
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(productEntityMapper::toProduct);
    }

    @Override
    public List<Product> findAll() {
        return products.stream().map(productEntityMapper::toProduct).toList();
    }

    @CacheEvict(value = "products", key = "#id")
    // Se utiliza para eliminar la informacion en cache cuando se elimine se producto y no quede ahì guardada
    @Override
    public void deleteById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
