package com.mini_autorizador.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CardResponse {

    @JsonProperty("numeroCartao")
    private String numberCard;

    private BigDecimal balance;

    public CardResponse() {
    }

    public CardResponse(String numberCard) {
        this.numberCard = numberCard;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
