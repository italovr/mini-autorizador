package com.mini_autorizador.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String card) {
        super(card);
    }
}
