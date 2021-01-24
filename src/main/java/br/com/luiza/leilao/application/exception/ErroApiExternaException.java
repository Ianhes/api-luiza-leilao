package br.com.luiza.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErroApiExternaException extends RuntimeException{
    public ErroApiExternaException(String message) {
        super(message);
    }
}
