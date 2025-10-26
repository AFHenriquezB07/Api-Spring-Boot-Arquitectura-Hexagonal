package com.cursodavinchicoder.product.application.query.getAll;

import com.cursodavinchicoder.common.mediator.RequestHandler;
import com.cursodavinchicoder.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllProductHandler implements RequestHandler<GetAllProductRequest, GetAllProductResponse> {


    private final ProductRepository producRepository;

    @Override
    public GetAllProductResponse handle(GetAllProductRequest request) {

        log.info("Getting all products");

        log.info("Found {} products", producRepository.findAll().size());

        return new GetAllProductResponse(producRepository.findAll());
    }

    @Override
    public Class<GetAllProductRequest> getRequestType() {
        return GetAllProductRequest.class;
    }
}
