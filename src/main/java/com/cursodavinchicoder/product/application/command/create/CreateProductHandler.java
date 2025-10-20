package com.cursodavinchicoder.product.application.command.create;

import com.cursodavinchicoder.common.mediator.RequestHandler;
import com.cursodavinchicoder.common.util.FileUtils;
import com.cursodavinchicoder.product.domain.entity.Product;
import com.cursodavinchicoder.product.domain.port.ProducRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductHandler implements RequestHandler<CreateProductRequest, Void> {


    private final ProducRepository producRepository;

    private final FileUtils fileUtils;

    @Override
    public Void handle(CreateProductRequest request) {

        String uniqueFileName = fileUtils.saveProductImage(request.getFile());

        Product product = Product.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(uniqueFileName)
                .build();

        producRepository.upsert(product);
        return null;
    }

    @Override
    public Class<CreateProductRequest> getRequestType() {
        return CreateProductRequest.class;
    }
}
