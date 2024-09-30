package com.mini_autorizador.service;

import reactor.core.publisher.Mono;

public interface PasswordService {
    Mono<String> hashPassword(String plainPassword);
    void checkPassword(String plainPassword, String hashedPassword);
}
