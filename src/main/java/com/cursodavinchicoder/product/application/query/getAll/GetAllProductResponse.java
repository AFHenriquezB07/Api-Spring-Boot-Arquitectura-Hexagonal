package com.cursodavinchicoder.product.application.query.getAll;

import com.cursodavinchicoder.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetAllProductResponse {

    private List<Product> products;
}
