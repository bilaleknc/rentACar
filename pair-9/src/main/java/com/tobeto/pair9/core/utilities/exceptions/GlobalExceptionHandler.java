package com.tobeto.pair9.core.utilities.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Class içerisinde global ex. handler methodlar bulunduğunu söyler.
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class}) // methodun içerisinde verilen türün ex. handler metodu olduğunu belirler
    @ResponseStatus(HttpStatus.BAD_REQUEST) // return işlevinin cevabı hangi http statüsünde döndüreceğini belirler.
    public String handleValidationError(MethodArgumentNotValidException exception)
    {
        // TODO: Exceptionin içeriğini inceleyerek tüm validasyon hatalarını alır ve hata mesajı olarak yazdırır.
        return "Validation Error";
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRuntimeException(RuntimeException exception)
    {
        return exception.getMessage();
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleError(Exception exception)
    {
        return "Unknown Error";
    }
}
