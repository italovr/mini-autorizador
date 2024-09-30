package com.mini_autorizador.exception;

public class ExistingCardException extends RuntimeException {
    public ExistingCardException(String card) {
        super(card);
    }
}
