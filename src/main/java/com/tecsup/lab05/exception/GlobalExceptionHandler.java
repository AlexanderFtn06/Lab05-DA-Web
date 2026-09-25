package com.tecsup.lab05.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> manejarErrores(MethodArgumentNotValidException ex){

        Map<String ,String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> manejarProductoNoEncontrado(ProductoNoEncontradoException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> manejarUnauthorized(UnauthorizedException ex){
        return ResponseEntity.status(401).body(ex.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<String> manejarForbidden(ForbiddenException ex) {
        return ResponseEntity.status(403).body(ex.getMessage());
    }
}
