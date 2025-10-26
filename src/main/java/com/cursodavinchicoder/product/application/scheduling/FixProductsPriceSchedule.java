package com.cursodavinchicoder.product.application.scheduling;

import com.cursodavinchicoder.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FixProductsPriceSchedule {

    private final ProductRepository productRepository;

    //Con esta anotacion hacemos que spring boot ejecute las tareas que nosotros queremos que se ejecuten en un tiempo determinado
    @Scheduled(fixedRate = 5000)
    public void fixProductsPrice() {
        log.info("Fixing products price schedule...");

        productRepository.findAll().stream().forEach(product -> {
            product.setPrice(product.getPrice() * 1.1);

            productRepository.upsert(product);
        });

        log.info("Fixed products price schedule complete!");
    }
}
