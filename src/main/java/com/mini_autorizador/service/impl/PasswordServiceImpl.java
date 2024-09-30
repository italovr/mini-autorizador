package com.mini_autorizador.service.impl;

import com.mini_autorizador.exception.PasswordInvalidException;
import com.mini_autorizador.service.PasswordService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class PasswordServiceImpl implements PasswordService {

    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Mono<String> hashPassword(String plainPassword) {
        return Mono.just(passwordEncoder.encode(plainPassword));
    }

    public void checkPassword(String plainPassword, String hashedPassword) {
        Optional.of(passwordEncoder.matches(plainPassword, hashedPassword))
                .filter(isMatch -> isMatch)
                .orElseThrow(() -> new PasswordInvalidException("Password invalid"));
    }


}
