package br.com.luiza.application.exception.handler;

import br.com.luiza.application.exception.RecursoNaoEncontradoException;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Erro Servidor", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecursoNaoEncontradoException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Recurso não encontrado", details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleUserArgumentNotValidException(ConstraintViolationException ex){
        List<String> details = new ArrayList<>();
        ex.getConstraintViolations().forEach(i -> {
                    details.add(i.getMessage());
                    String path  = i.getPropertyPath().toString().split("\\.")[1];
                    details.add(path);
                });
        ErrorResponse error = new ErrorResponse("Parametros passados errado ", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        List<String> errorFields = ex.getBindingResult().getFieldErrors().stream()
                .map(field -> field.getField())
                .collect(Collectors.toList());
        details.add(" Erro no corpo da requisição " + ex.getFieldError().getDefaultMessage());
        details.addAll(errorFields);
        ErrorResponse error = new ErrorResponse("Falha na validação ", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

}
