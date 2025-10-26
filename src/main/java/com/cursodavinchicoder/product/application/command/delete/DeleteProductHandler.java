package com.cursodavinchicoder.product.application.command.delete;

import com.cursodavinchicoder.common.mediator.RequestHandler;
import com.cursodavinchicoder.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteProductHandler implements RequestHandler<DeleteProductRequest, Void> {


    private final ProductRepository producRepository;

    @Override
    public Void handle(DeleteProductRequest request) {

        log.info("Deleting product {}", request.getId());

        /*

        // Este codigo se utilizo para probar la funcionalidad de Async de spring boot haciendo que se retarde 5 segundo la peticion

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        */

        producRepository.deleteById(request.getId());

        log.info("Product {} has been deleted", request.getId());

        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequestType() {
        return DeleteProductRequest.class;
    }
}
