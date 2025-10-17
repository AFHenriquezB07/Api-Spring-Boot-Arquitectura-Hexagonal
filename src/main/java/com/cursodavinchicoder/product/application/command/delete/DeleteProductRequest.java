package com.cursodavinchicoder.product.application.command.delete;

import com.cursodavinchicoder.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteProductRequest implements Request<Void> {

    private Long id;
}
