package com.mini_autorizador.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.mini_autorizador.bo.Card;
import com.mini_autorizador.exception.CardNotFoundException;
import com.mini_autorizador.exception.ExistingCardException;
import com.mini_autorizador.repository.CardRepository;
import com.mini_autorizador.repository.model.CardModel;
import com.mini_autorizador.service.impl.CardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Optional;

public class CardServiceImplTest {

    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private PasswordService passwordService;

    @Mock
    private CardRepository cardRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createCard_ShouldReturnCard_WhenSuccessful() {
        Card card = new Card(1L, "1234", "123", BigDecimal.valueOf(500.0));
        CardModel cardModel = new CardModel(1L, "1234567890123456", "123", BigDecimal.valueOf(500.0));
        when(cardRepository.save(any())).thenReturn(cardModel);
        when(passwordService.hashPassword(anyString())).thenReturn(Mono.just("encryptedPassword"));

        Mono<Card> result = cardService.createCard(card);

        assertEquals(card, result.block());
        verify(cardRepository).save(any());
    }

    @Test
    public void createCard_ShouldReturnError_WhenCardExists() {
        Card card = new Card(1L, "1234567890123456", "123", BigDecimal.valueOf(500.0));
        when(cardRepository.save(any())).thenThrow(new RuntimeException("Card already exists"));
        when(passwordService.hashPassword(any())).thenReturn(Mono.just("1231123222"));

        Mono<Card> result = cardService.createCard(card);

        assertThrows(ExistingCardException.class, result::block);
    }

    @Test
    public void getBalanceByNumberCard_ShouldReturnCard_WhenExists() {
        CardModel cardModel = new CardModel(1L, "1234567890123456", "123", BigDecimal.valueOf(500.0));
        when(cardRepository.findByNumberCard("1234567890123456")).thenReturn(Optional.of(cardModel));

        Mono<Card> result = cardService.getBalanceByNumberCard("1234567890123456");

        assertEquals("1234567890123456", result.block().getNumberCard());
    }

    @Test
    public void getBalanceByNumberCard_ShouldThrowError_WhenNotFound() {
        when(cardRepository.findByNumberCard("1234567890123456")).thenReturn(Optional.empty());

        Mono<Card> result = cardService.getBalanceByNumberCard("1234567890123456");

        assertThrows(CardNotFoundException.class, result::block);
    }

    @Test
    public void updateBalance_ShouldUpdateBalance_WhenSuccessful() {
        Card card = new Card(1L, "1234567890123456", "123", BigDecimal.valueOf(500.0));
        CardModel cardModel = new CardModel(1L, "1234567890123456", "123", BigDecimal.valueOf(500.0));

        when(cardRepository.save(any(CardModel.class))).thenReturn(cardModel);

        assertDoesNotThrow(() -> cardService.updateBalance(card).block());
        verify(cardRepository).save(any(CardModel.class));
    }


}

