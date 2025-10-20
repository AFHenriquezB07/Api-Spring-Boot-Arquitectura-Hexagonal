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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final Mediator mediator;

    private final ProductMapper productMapper;

    @GetMapping("/products/params")
    public ResponseEntity<List<ProductDto>> getAllProduct(@RequestParam(required = false) String pageSize) {

        GetAllProductResponse response = mediator.dispatch(new GetAllProductRequest());

//        List<ProductDto> productDtos = response.getProducts().stream().map(product -> productMapper.mapToProductDto(product)).toList();

//        Otra manera de hacerlo
        List<ProductDto> productDtos = response.getProducts().stream().map(productMapper::mapToProductDto).toList();

        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {

        GetProductByIdResponse response = mediator.dispatch(new GetProductByIdRequest(id));

        ProductDto productDto = productMapper.mapToProductDto(response.getProduct());

        return ResponseEntity.ok(productDto);
    }

    @PostMapping("/save_product")
    public ResponseEntity<Void> createProduct(@ModelAttribute @Valid CreateProductDto productDto) {

        CreateProductRequest request = productMapper.mapToCreateProductRequest(productDto);

        mediator.dispatch(request);
        return ResponseEntity.created(URI.create("/api/v1/save_product/".concat(request.getId().toString()))).build();
    }

    @PutMapping("/update/product")
    public ResponseEntity<Void> updateProduct(@ModelAttribute @Valid UpdateProductDto productDto) {

        UpdateProductRequest request = productMapper.mapToUpdateProductRequest(productDto);

        mediator.dispatch(request);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        mediator.dispatch(new DeleteProductRequest(id));

        return ResponseEntity.noContent().build();
    }
}
