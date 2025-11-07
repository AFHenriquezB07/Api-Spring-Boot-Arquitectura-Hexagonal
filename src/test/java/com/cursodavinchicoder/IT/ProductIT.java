package com.cursodavinchicoder.IT;

import com.cursodavinchicoder.product.domain.entity.Product;
import com.cursodavinchicoder.product.domain.port.ProductRepository;
import com.cursodavinchicoder.product.infrastructure.api.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
@AutoConfigureMockMvc // Esta anotacion nos sirve para que nos auto configure el Mock MVC
public class ProductIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc; // Manera para poder nosotros poder probar archivos que venga por multipar, en este caso la imagen que estamos enviando

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() { // Metodo que se ejecutara antes de un metodo
        log.info("Setting up integration tests");
        productRepository.upsert(
                Product.builder().id(1L).name("Product 1").description("Description 1").price(100.0).build()
        );
    }

    @AfterEach
    void tearDown() { // Metodo que se ejecutara despues de un metodo
        log.info("Tearing down integration tests");
        productRepository.deleteById(1L);
    }

    @Test
    public void getProductBuIdExist() {
        ResponseEntity<ProductDto> response = restTemplate.getForEntity("http://localhost:8080/api/v1/product/1", ProductDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        assertEquals("Product 1", response.getBody().getName());
        assertEquals("Description 1", response.getBody().getDescription());
        assertEquals(100.0, response.getBody().getPrice());
    }

    @Test
    public void saveProduct() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "image.jpeg", "image/jpeg", "image".getBytes());

        mockMvc.perform(
                multipart(HttpMethod.POST, "/api/v1/save_product")
                        .file(file)
                        .param("id", "2")
                        .param("name", "Name 2")
                        .param("description", "Description 2")
                        .param("price", "150.00")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
        ).andExpect(status().isCreated());
    }

}
