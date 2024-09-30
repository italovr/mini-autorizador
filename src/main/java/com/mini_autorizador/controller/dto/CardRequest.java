package com.mini_autorizador.controller.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CardRequest {

    @JsonProperty("numeroCartao")
    @NotBlank(message = "O número do cartão não pode estar em branco.")
    @Size(min = 16, max = 16, message = "O número do cartão deve ter 16 dígitos.")
    private String numberCard;

    @JsonProperty("senha")
    @JsonAlias({ "senhaCartao"})
    @NotBlank(message = "A senha não pode estar em branco.")
    @Size(min = 4, max = 8, message = "A senha deve ter entre 4 e 8 caracteres.")
    private String password;

    public CardRequest() {
    }

    public CardRequest(String numberCard, String password) {
        this.numberCard = numberCard;
        this.password = password;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public String getPassword() {
        return password;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}