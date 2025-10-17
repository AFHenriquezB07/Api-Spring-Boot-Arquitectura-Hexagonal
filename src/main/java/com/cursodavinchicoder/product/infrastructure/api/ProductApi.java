package com.cursodavinchicoder.product.infrastructure.api;


import com.cursodavinchicoder.product.infrastructure.api.dto.ProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductApi {

    ResponseEntity<List<ProductDto>> getAllProduct(String pageSize);

    ResponseEntity<ProductDto> getProductById(Long id);

    ResponseEntity<Void> createProduct(ProductDto product);

    ResponseEntity<Void> updateProduct(ProductDto product);

    ResponseEntity<Void> deleteProduct(Long id);
}
