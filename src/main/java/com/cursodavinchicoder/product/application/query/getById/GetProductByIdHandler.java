package com.cursodavinchicoder.product.application.query.getById;

import com.cursodavinchicoder.common.mediator.RequestHandler;
import com.cursodavinchicoder.product.domain.entity.Product;
import com.cursodavinchicoder.product.domain.exception.ProductNotFoundException;
import com.cursodavinchicoder.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetProductByIdHandler implements RequestHandler<GetProductByIdRequest, GetProductByIdResponse> {


    private final ProductRepository producRepository;

    @Override
    public GetProductByIdResponse handle(GetProductByIdRequest request) {

        log.info("Getting product by id {}", request.getId());

        Product product = producRepository.findById(request.getId()).orElseThrow(() -> new ProductNotFoundException(request.getId()));

        log.info("Product has whit in {}", request.getId());

        return new GetProductByIdResponse(product);
    }

    @Override
    public Class<GetProductByIdRequest> getRequestType() {
        return GetProductByIdRequest.class;
    }
}
