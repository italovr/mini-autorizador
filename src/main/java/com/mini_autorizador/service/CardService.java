package com.mini_autorizador.service;

import com.mini_autorizador.bo.Card;
import reactor.core.publisher.Mono;

public interface CardService {

    Mono<Card> createCard(Card card);

    Mono<Card> getBalanceByNumberCard(String numberCard);

    Mono<Card> getCardInfo(String numberCard);

    Mono<Void> updateBalance(Card card);
}
