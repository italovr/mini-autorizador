package com.mini_autorizador.service.impl;

import com.mini_autorizador.bo.Card;
import com.mini_autorizador.exception.CardNotFoundException;
import com.mini_autorizador.exception.CardNotFoundTransactionalException;
import com.mini_autorizador.exception.ExistingCardException;
import com.mini_autorizador.repository.CardRepository;
import com.mini_autorizador.repository.model.CardModel;
import com.mini_autorizador.service.CardService;
import com.mini_autorizador.service.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Optional;

import static com.mini_autorizador.util.CardMapper.modelToCard;
import static com.mini_autorizador.util.CardMapper.toCardModel;

@Service
public class CardServiceImpl implements CardService {

    private static final Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);

    @Autowired
    private final PasswordService passwordService;
    private final CardRepository cardRepository;

    public CardServiceImpl(PasswordService passwordService, CardRepository cardRepository) {
        this.passwordService = passwordService;
        this.cardRepository = cardRepository;
    }

    @Override
    public Mono<Card> createCard(Card card) {
        return setPasswordEncrypt(card)
                .then(setBalance(card))
                .then(Mono.fromCallable(() -> cardRepository.save(toCardModel(card))))
                .map(savedCard -> {
                    return card;
                })
                .onErrorResume(ex -> {
                    logger.error(ex.getMessage(), ex);
                    return Mono.error(new ExistingCardException(card.getNumberCard()));
                });
    }

    @Override
    public Mono<Card> getBalanceByNumberCard(String numberCard) {
        return Mono.fromCallable(() -> getByNumberCard(numberCard))
                .flatMap(cardModel -> {
                    return cardModel.map(model ->
                            Mono.just(modelToCard(model))).orElseGet(() ->
                            Mono.error(new CardNotFoundException()));
                });
    }

    @Override
    public Mono<Card> getCardInfo(String numberCard) {
        return Mono.fromCallable(() -> getByNumberCard(numberCard))
                .flatMap(cardModel -> {
                    return cardModel.map(model ->
                            Mono.just(modelToCard(model))).orElseGet(() ->
                            Mono.error(new CardNotFoundTransactionalException()));
                });
    }

    @Override
    public Mono<Void> updateBalance(Card card) {
        CardModel cardModel = toCardModel(card);
        return Mono.just(cardRepository.save(cardModel)).then();
    }


    private Optional<CardModel> getByNumberCard(String numberCard) {
        return cardRepository.findByNumberCard(numberCard);
    }


    private Mono<Void> setPasswordEncrypt(Card card) {
        return passwordService.hashPassword(card.getPassword())
                .doOnNext(card::setPassword)
                .then();
    }

    private Mono<Void> setBalance(Card card) {
        card.setBalance(new BigDecimal(500));
        return Mono.empty();
    }

}
