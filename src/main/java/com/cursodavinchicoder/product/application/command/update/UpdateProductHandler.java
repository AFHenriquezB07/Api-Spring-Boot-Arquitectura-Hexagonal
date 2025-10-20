package com.cursodavinchicoder.product.application.command.update;

import com.cursodavinchicoder.common.mediator.RequestHandler;
import com.cursodavinchicoder.common.util.FileUtils;
import com.cursodavinchicoder.product.domain.entity.Product;
import com.cursodavinchicoder.product.domain.port.ProducRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest, Void> {


    private final ProducRepository producRepository;

    private final FileUtils fileUtils;

    @Override
    public Void handle(UpdateProductRequest request) {

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
    public Class<UpdateProductRequest> getRequestType() {
        return UpdateProductRequest.class;
    }
}
