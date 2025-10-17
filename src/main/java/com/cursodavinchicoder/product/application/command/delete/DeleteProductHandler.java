package com.cursodavinchicoder.product.application.command.delete;

import com.cursodavinchicoder.common.mediator.RequestHandler;
import com.cursodavinchicoder.product.domain.port.ProducRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductHandler implements RequestHandler<DeleteProductRequest, Void> {


    private final ProducRepository producRepository;

    @Override
    public Void handle(DeleteProductRequest request) {

        producRepository.deleteById(request.getId());

        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequestType() {
        return DeleteProductRequest.class;
    }
}
