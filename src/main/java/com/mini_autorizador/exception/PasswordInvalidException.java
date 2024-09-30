package com.mini_autorizador.exception;

public class PasswordInvalidException extends RuntimeException {
    public PasswordInvalidException(String card) {
        super(card);
    }
}
