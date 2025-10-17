package com.cursodavinchicoder.product.infrastructure.api.mapper;

import com.cursodavinchicoder.product.application.command.create.CreateProductRequest;
import com.cursodavinchicoder.product.application.command.update.UpdateProductRequest;
import com.cursodavinchicoder.product.domain.entity.Product;
import com.cursodavinchicoder.product.infrastructure.api.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

    CreateProductRequest mapToCreateProductRequest(ProductDto productDto);

    UpdateProductRequest mapToUpdateProductRequest(ProductDto productDto);

    ProductDto mapToProductDto(Product product);
}
