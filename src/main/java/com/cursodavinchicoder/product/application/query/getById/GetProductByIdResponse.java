package com.cursodavinchicoder.product.application.query.getById;

import com.cursodavinchicoder.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductByIdResponse {

    private Product product;
}
