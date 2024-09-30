package com.mini_autorizador.service;

import com.mini_autorizador.bo.Transaction;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Mono<Transaction> processTransaction(Transaction transaction);

}
