package com.cursodavinchicoder.product.application.command.create;

import com.cursodavinchicoder.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateProductResponse {

    private Product product;
}
