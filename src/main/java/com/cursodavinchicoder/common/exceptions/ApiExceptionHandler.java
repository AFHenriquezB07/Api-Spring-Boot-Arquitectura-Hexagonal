package com.cursodavinchicoder.common.exceptions;

import com.cursodavinchicoder.product.domain.exception.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    // Esta anotacion hace que cuando se lance una exepcion de este tipo va a pasar por este metodo
    @ResponseBody // Esta anotacion lo que hace es que se devolvera una respuesta en la peticion http
    public ErrorMessage badRequest(HttpServletRequest request, MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        /*
            Nota: Se obtine con getBindingResult() los resultados que estan bildeados para cada uno de los atributos
                  lugo con getFieldErrors() que son los campos de la peticion que han fallado acedemos por cada uno de
                  ellos que han fallado y para nuesto map que la clave va a hacer el campo y el valor el mensaje que
                  tiene la exepcion o el error
        */


        return new ErrorMessage(exception.getMessage(), exception.getClass().getSimpleName(), request.getRequestURI(), errors);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    public ErrorMessage notFound(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception.getMessage(), exception.getClass().getSimpleName(), request.getRequestURI());
    }
}
