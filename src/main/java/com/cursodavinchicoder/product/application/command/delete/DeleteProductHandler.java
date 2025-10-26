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

        System.out.println("Eliminando producto con id: " + request.getId() + "...");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        producRepository.deleteById(request.getId());

        System.out.println("Producto eliminado con id: " + request.getId() + "...");

        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequestType() {
        return DeleteProductRequest.class;
    }
}
