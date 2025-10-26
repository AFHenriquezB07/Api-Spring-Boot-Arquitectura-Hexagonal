package com.cursodavinchicoder.product.infrastructure.api;

import com.cursodavinchicoder.common.mediator.Mediator;
import com.cursodavinchicoder.product.application.command.create.CreateProductRequest;
import com.cursodavinchicoder.product.application.command.delete.DeleteProductRequest;
import com.cursodavinchicoder.product.application.command.update.UpdateProductRequest;
import com.cursodavinchicoder.product.application.query.getAll.GetAllProductRequest;
import com.cursodavinchicoder.product.application.query.getAll.GetAllProductResponse;
import com.cursodavinchicoder.product.application.query.getById.GetProductByIdRequest;
import com.cursodavinchicoder.product.application.query.getById.GetProductByIdResponse;
import com.cursodavinchicoder.product.infrastructure.api.dto.CreateProductDto;
import com.cursodavinchicoder.product.infrastructure.api.dto.ProductDto;
import com.cursodavinchicoder.product.infrastructure.api.dto.UpdateProductDto;
import com.cursodavinchicoder.product.infrastructure.api.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ProductController implements ProductApi {

    private final Mediator mediator;

    private final ProductMapper productMapper;

    @GetMapping("/products/params")
    public ResponseEntity<List<ProductDto>> getAllProduct(@RequestParam(required = false) String pageSize) {

        log.info("Getting all products");

        GetAllProductResponse response = mediator.dispatch(new GetAllProductRequest());

//        List<ProductDto> productDtos = response.getProducts().stream().map(product -> productMapper.mapToProductDto(product)).toList();

//        Otra manera de hacerlo
        List<ProductDto> productDtos = response.getProducts().stream().map(productMapper::mapToProductDto).toList();

        log.info("Found {} products", productDtos.size());

        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {

        log.info("Getting product by id: {}", id);

        GetProductByIdResponse response = mediator.dispatch(new GetProductByIdRequest(id));

        ProductDto productDto = productMapper.mapToProductDto(response.getProduct());

        log.info("Found product by id: {}", productDto.getId());

        return ResponseEntity.ok(productDto);
    }

    @PostMapping("/save_product")
    public ResponseEntity<Void> createProduct(@ModelAttribute @Valid CreateProductDto productDto) {

        log.info("Creating product {}", productDto.getId());

        CreateProductRequest request = productMapper.mapToCreateProductRequest(productDto);

        mediator.dispatch(request);

        log.info("Created product {}", productDto.getId());

        return ResponseEntity.created(URI.create("/api/v1/save_product/".concat(request.getId().toString()))).build();
    }

    @PutMapping("/update/product")
    public ResponseEntity<Void> updateProduct(@ModelAttribute @Valid UpdateProductDto productDto) {

        log.info("Updating product {}", productDto.getId());

        UpdateProductRequest request = productMapper.mapToUpdateProductRequest(productDto);

        mediator.dispatch(request);

        log.info("Updated product {}", productDto.getId());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        log.info("Deleting product {}", id);

        mediator.dispatch(new DeleteProductRequest(id));

        log.info("Deleted product {}", id);

        return ResponseEntity.noContent().build();
    }

    // Metodo para probar lo de la asincronia
    @DeleteMapping("/delete/async/{id}")
    public ResponseEntity<Void> deleteProductAsync(@PathVariable Long id) {

        log.info("Deleting product {}", id);

        mediator.dispatchAsync(new DeleteProductRequest(id));

        log.info("Deleted product {}", id);

        return ResponseEntity.accepted().build();
    }
}
