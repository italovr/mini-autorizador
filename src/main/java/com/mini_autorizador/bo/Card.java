package com.mini_autorizador.bo;


import java.math.BigDecimal;

public class Card {
    private Long id;
    private String numberCard;
    private String password;
    private BigDecimal balance;

    public Card(){}

    public Card(Long id, String numberCard, String password, BigDecimal balance){
        this.id=id;
        this.numberCard=numberCard;
        this.password = password;
        this.balance = balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

