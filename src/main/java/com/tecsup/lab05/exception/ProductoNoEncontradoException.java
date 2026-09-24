package com.tecsup.lab05.exception;

public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(Long id) {
        super("No existe un producto con ID: " + id);
    }
}