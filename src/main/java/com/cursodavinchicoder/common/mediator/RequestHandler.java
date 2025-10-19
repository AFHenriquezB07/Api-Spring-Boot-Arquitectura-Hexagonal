package com.cursodavinchicoder.common.mediator;

public interface RequestHandler<T extends Request<R>, R> {

    R handle(T request); // Este metodo se utiliza para hacer la respuesta a la peticion que resivamos

    Class<T> getRequestType(); // Este metodo es para saber que tipo de clase es la que se esta resiviendo en la peticion(request)
}
