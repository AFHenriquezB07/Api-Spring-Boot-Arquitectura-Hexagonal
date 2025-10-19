package com.cursodavinchicoder.common.mediator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Mediator {

    Map<? extends Class<?>, RequestHandler<?, ?>> requestHandlerMap;

    /*
     Nota: -> Explicacion del constructo:
         Todas las clases que nosotros tengamos en nuestra capa de aplicacion que son los Handler(Casos de Usos) que imiplemente
         la interfaz RequestHandler, automaticamente srping boot los va a inyectar en este constructor
     */
    public Mediator(List<RequestHandler<?, ?>> requestHandlers) {
        requestHandlerMap = requestHandlers.stream().collect(Collectors.toMap(RequestHandler::getRequestType, Function.identity()));
        /* Una vez que estan inyectados como tenemos esas dos funciones de la interfaz, lo que se hace es crearce un map
           clave valor donde se alamacena como clave el tipo de la clase y el valor la Clase que lo maneja.
         */
    }

    public <R, T extends Request<R>> R dispatch(T request) {
        RequestHandler<T, R> requestHandler = (RequestHandler<T, R>) requestHandlerMap.get(request.getClass());

        if (requestHandler == null) {
            throw new RuntimeException("No handler found for request type: " + request.getClass());
        }

        return requestHandler.handle(request);
    }
}
