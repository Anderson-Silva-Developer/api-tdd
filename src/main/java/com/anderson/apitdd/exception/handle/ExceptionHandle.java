package com.anderson.apitdd.exception.handle;

import com.anderson.apitdd.exception.BadRequestGeneric;
import com.anderson.apitdd.exception.ErrorDetails;
import com.anderson.apitdd.exception.NotFoundGeneric;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(NotFoundGeneric.class)
    public ResponseEntity<ErrorDetails> InternaalException(NotFoundGeneric ex) {

        ErrorDetails erro = new ErrorDetails();
        erro.setTitulo("Not Found");
        erro.setStatus(404L);
        erro.setMenssagemDesenvolvedor(ex.getMessage());
        erro.setTimestap(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);

    }
    @ExceptionHandler(BadRequestGeneric.class)
    public ResponseEntity<ErrorDetails> InternaalException(BadRequestGeneric ex) {

        ErrorDetails erro = new ErrorDetails();
        erro.setTitulo("Bad Request");
        erro.setStatus(400L);
        erro.setMenssagemDesenvolvedor(ex.getMessage());
        erro.setTimestap(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> InternaalException(MethodArgumentNotValidException ex) {

        ErrorDetails erro = new ErrorDetails();
        erro.setTitulo("Bad Request");
        erro.setStatus(400L);
        String messaemErro = ex.getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        erro.setMenssagemDesenvolvedor(messaemErro);
        erro.setTimestap(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);

    }
}
