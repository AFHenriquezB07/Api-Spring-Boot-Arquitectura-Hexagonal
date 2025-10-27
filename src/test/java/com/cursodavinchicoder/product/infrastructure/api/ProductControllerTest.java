package com.cursodavinchicoder.product.infrastructure.api;

import com.cursodavinchicoder.common.mediator.Mediator;
import com.cursodavinchicoder.product.application.query.getAll.GetAllProductRequest;
import com.cursodavinchicoder.product.application.query.getAll.GetAllProductResponse;
import com.cursodavinchicoder.product.domain.entity.Product;
import com.cursodavinchicoder.product.infrastructure.api.dto.ProductDto;
import com.cursodavinchicoder.product.infrastructure.api.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //No sirve para que se habilite todos los mock que tengamos
class ProductControllerTest {

    @Mock // anotacion que hace referencia a que va a crear una instancia para traernos todas sus funcionalidades
    private Mediator mediator;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks // Anotacion para decirle que a esa clase se le va a inyectar las pruebas
    private ProductController productController;

    @Test // Se le coloca esta anotacion para que el metodo pueda ser testeado
    public void getAllProducts() {

        GetAllProductResponse getAllProductResponse = new GetAllProductResponse(List.of(
                Product.builder().id(1L).build(),
                Product.builder().id(2L).build()
        ));

        when(mediator.dispatch(new GetAllProductRequest())).thenReturn(getAllProductResponse);

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        when(productMapper.mapToProductDto(any(Product.class))).thenReturn(productDto);

        ResponseEntity<List<ProductDto>> response = productController.getAllProduct("5");

        assertEquals(HttpStatus.OK, response.getStatusCode()); // Validar de que la respuesta sea ok
        assertNotNull(response.getBody()); // Validar de que no venga nula

        List<ProductDto> productDtos = response.getBody();
        assertEquals(2, productDtos.size()); // Validar que lo que traemos sea igual a 5
    }

}