package com.cursodavinchicoder.product.application.command.update;

import com.cursodavinchicoder.common.mediator.Request;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateProductRequest implements Request<Void> {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private MultipartFile file;
}
