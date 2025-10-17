package com.cursodavinchicoder.product.application.query.getAll;

import com.cursodavinchicoder.common.mediator.RequestHandler;
import com.cursodavinchicoder.product.domain.port.ProducRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllProductHandler implements RequestHandler<GetAllProductRequest, GetAllProductResponse> {


    private final ProducRepository producRepository;

    @Override
    public GetAllProductResponse handle(GetAllProductRequest request) {

        return new GetAllProductResponse(producRepository.findAll());
    }

    @Override
    public Class<GetAllProductRequest> getRequestType() {
        return GetAllProductRequest.class;
    }
}
