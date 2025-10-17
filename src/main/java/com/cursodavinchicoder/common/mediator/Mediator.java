package com.cursodavinchicoder.common.mediator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Mediator {

    Map<? extends Class<?>, RequestHandler<?, ?>> requestHandlerMap;

    public Mediator(List<RequestHandler<?, ?>> requestHandlers) {
        requestHandlerMap = requestHandlers.stream().collect(Collectors.toMap(RequestHandler::getRequestType, Function.identity()));
    }

    public <R, T extends Request<R>> R dispatch(T request) {
        RequestHandler<T, R> requestHandler = (RequestHandler<T, R>) requestHandlerMap.get(request.getClass());

        if (requestHandler == null) {
            throw new RuntimeException("No handler found for request type: " + request.getClass());
        }

        return requestHandler.handle(request);
    }
}
