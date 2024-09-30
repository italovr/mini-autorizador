package com.mini_autorizador.util;

import com.mini_autorizador.bo.Card;
import com.mini_autorizador.controller.dto.CardRequest;
import com.mini_autorizador.controller.dto.CardResponse;
import com.mini_autorizador.repository.model.CardModel;

public class CardMapper {
    public static Card toCard(CardRequest cardRequest) {
        Card card = new Card();
        card.setNumberCard(cardRequest.getNumberCard());
        card.setPassword(cardRequest.getPassword());
        return card;
    }

    public static CardModel toCardModel(Card card) {

        CardModel cardModel = new CardModel();
        cardModel.setId(card.getId());
        cardModel.setNumberCard(card.getNumberCard());
        cardModel.setPassword(card.getPassword());
        cardModel.setBalance(card.getBalance());
        return cardModel;
    }

    public static Card modelToCard(CardModel model) {
        Card card = new Card();
        card.setId(model.getId());
        card.setNumberCard(model.getNumberCard());
        card.setBalance(model.getBalance());
        card.setPassword(model.getPassword());
        return card;
    }

    public static CardResponse toCardResponse(Card card) {

        CardResponse cardResponse = new CardResponse();
        cardResponse.setNumberCard(card.getNumberCard());
        cardResponse.setBalance(card.getBalance());
        return cardResponse;
    }
}
