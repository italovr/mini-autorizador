package com.mini_autorizador.controller;

import com.mini_autorizador.controller.dto.CardRequest;
import com.mini_autorizador.controller.dto.CardResponse;
import com.mini_autorizador.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import static com.mini_autorizador.util.CardMapper.toCard;
import static com.mini_autorizador.util.CardMapper.toCardResponse;

@RestController
@RequestMapping("/cartoes")
public class CardController {

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public Mono<ResponseEntity<CardResponse>> createCard(@RequestBody @Validated CardRequest cardRequest) {
        logger.info("Received request to create card: {}", cardRequest.getNumberCard());

        return cardService.createCard(toCard(cardRequest))
                .doOnNext(createdCard -> logger.info("Card created successfully: {}", createdCard.getNumberCard()))
                .map(createdCard -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(toCardResponse(createdCard))
                );
    }

    @GetMapping("/{numberCard}")
    public Mono<ResponseEntity<CardResponse>> getBalance(@PathVariable String numberCard) {
        return cardService.getBalanceByNumberCard(numberCard)
                .map(card -> ResponseEntity.ok(toCardResponse(card)));
    }

}
