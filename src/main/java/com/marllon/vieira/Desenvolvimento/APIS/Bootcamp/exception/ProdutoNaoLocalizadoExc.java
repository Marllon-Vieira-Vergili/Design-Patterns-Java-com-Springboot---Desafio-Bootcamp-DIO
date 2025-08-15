package com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.exception;


/**
 * Exception Customizada se produto não for localizado
 *
 * */
public class ProdutoNaoLocalizadoExc extends RuntimeException {
    public ProdutoNaoLocalizadoExc(String message) {
        super(message);
    }
}
