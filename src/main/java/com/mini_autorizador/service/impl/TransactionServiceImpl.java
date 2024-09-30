package com.mini_autorizador.service.impl;

import com.mini_autorizador.bo.Card;
import com.mini_autorizador.bo.Transaction;
import com.mini_autorizador.exception.InsufficientBalanceException;
import com.mini_autorizador.service.CardService;
import com.mini_autorizador.service.PasswordService;
import com.mini_autorizador.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private final PasswordService passwordService;
    private final CardService cardService;

    public TransactionServiceImpl(PasswordService passwordService, CardService cardService) {
        this.passwordService = passwordService;
        this.cardService = cardService;
    }

    @Override
    public Mono<Transaction> processTransaction(Transaction transaction) {
        return validationTransaction(transaction)
                .flatMap(card -> updateBalance(card, transaction))
                .then(Mono.just(transaction));
    }

    private Mono<Card> validationTransaction(Transaction transaction) {
        return cardService.getCardInfo(transaction.getNumberCard())
                .flatMap(card ->
                        validationPassword(card, transaction)
                                .then(validationBalance(card, transaction))
                                .then(Mono.just(card))
                );
    }

    private Mono<Void> validationPassword(Card card, Transaction transaction) {
        passwordService.checkPassword(transaction.getPassword(), card.getPassword());
        return Mono.empty();
    }

    private Mono<Void> validationBalance(Card card, Transaction transaction) {
        return Mono.just(transaction.getValue().compareTo(card.getBalance()) > 0)
                .flatMap(isInsufficient -> {
                    if (isInsufficient) {
                        return Mono.error(new InsufficientBalanceException("Insufficient balance"));
                    }
                    return Mono.empty();
                });
    }

    private Mono<Void> updateBalance(Card card, Transaction transaction) {
        card.setBalance(card.getBalance().subtract(transaction.getValue()));
        return cardService.updateBalance(card);
    }
}
