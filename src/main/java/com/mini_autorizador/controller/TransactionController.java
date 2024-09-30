package com.mini_autorizador.controller;

import com.mini_autorizador.bo.Transaction;
import com.mini_autorizador.controller.dto.TransactionRequest;
import com.mini_autorizador.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;

import static com.mini_autorizador.util.TransactionMapper.toTransaction;

@RestController
@RequestMapping("/transacoes")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> transaction(@RequestBody @Validated TransactionRequest transactionRequest) {
        logger.info("Received request to transaction: {}", transactionRequest.getNumberCard());

        Transaction transaction = toTransaction(transactionRequest);

        return transactionService.processTransaction(transaction)
                .doOnNext(createdCard -> logger.info("Transaction created successfully: {}", createdCard))
                .map(createdCard -> ResponseEntity.status(HttpStatus.CREATED).build());
    }


}
