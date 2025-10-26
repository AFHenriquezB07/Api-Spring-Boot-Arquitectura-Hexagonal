package com.cursodavinchicoder.product.application.command.update;

import com.cursodavinchicoder.common.mediator.RequestHandler;
import com.cursodavinchicoder.common.util.FileUtils;
import com.cursodavinchicoder.product.domain.entity.Product;
import com.cursodavinchicoder.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest, Void> {


    private final ProductRepository producRepository;

    private final FileUtils fileUtils;

    @Override
    public Void handle(UpdateProductRequest request) {

        log.info("Update product {}", request.getId());

        String uniqueFileName = fileUtils.saveProductImage(request.getFile());

        Product product = Product.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(uniqueFileName)
                .build();

        producRepository.upsert(product);

        log.info("Product {} has been created", request.getId());

        return null;
    }


    @Override
    public Class<UpdateProductRequest> getRequestType() {
        return UpdateProductRequest.class;
    }
}
