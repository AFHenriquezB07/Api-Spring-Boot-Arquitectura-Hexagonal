package com.cursodavinchicoder.product.infrastructure.api;


import com.cursodavinchicoder.product.infrastructure.api.dto.CreateProductDto;
import com.cursodavinchicoder.product.infrastructure.api.dto.ProductDto;
import com.cursodavinchicoder.product.infrastructure.api.dto.UpdateProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductApi {

    ResponseEntity<List<ProductDto>> getAllProduct(String pageSize);

    ResponseEntity<ProductDto> getProductById(Long id);

    ResponseEntity<Void> createProduct(CreateProductDto product);

    ResponseEntity<Void> updateProduct(UpdateProductDto product);

    ResponseEntity<Void> deleteProduct(Long id);
}
