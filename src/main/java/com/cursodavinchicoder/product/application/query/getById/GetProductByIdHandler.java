package com.cursodavinchicoder.product.application.query.getById;

import com.cursodavinchicoder.common.mediator.RequestHandler;
import com.cursodavinchicoder.product.domain.entity.Product;
import com.cursodavinchicoder.product.domain.exception.ProductNotFoundException;
import com.cursodavinchicoder.product.domain.port.ProducRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductByIdHandler implements RequestHandler<GetProductByIdRequest, GetProductByIdResponse> {


    private final ProducRepository producRepository;

    @Override
    public GetProductByIdResponse handle(GetProductByIdRequest request) {

        Product product = producRepository.findById(request.getId()).orElseThrow(() -> new ProductNotFoundException(request.getId()));

        return new GetProductByIdResponse(product);
    }

    @Override
    public Class<GetProductByIdRequest> getRequestType() {
        return GetProductByIdRequest.class;
    }
}
