package com.cursodavinchicoder.product.application.query.getById;

import com.cursodavinchicoder.product.domain.entity.Product;
import com.cursodavinchicoder.product.domain.exception.ProductNotFoundException;
import com.cursodavinchicoder.product.domain.port.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetProductByIdHandlerTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductByIdHandler getProductByIdHandler;

    @Test
    void getProductById() {
        Long productId = 1L;
        Product mockProduct = Product.builder().id(productId).build();
        GetProductByIdRequest request = new GetProductByIdRequest(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        GetProductByIdResponse response = getProductByIdHandler.handle(request);

        assertNotNull(response);
        assertEquals(mockProduct, response.getProduct());
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        Long productId = 1L;
        GetProductByIdRequest request = new GetProductByIdRequest(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> getProductByIdHandler.handle(request));
    }

}