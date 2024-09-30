package com.mini_autorizador.bo;

import java.math.BigDecimal;

public class Transaction extends Card {

    private BigDecimal value;

    public Transaction(BigDecimal value, String numberCard, String password) {
        this.value = value;
        setNumberCard(numberCard);
        setPassword(password);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setBalance(BigDecimal balance) {
        super.setBalance(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return super.getBalance();
    }

    @Override
    public void setNumberCard(String numberCard) {
        super.setNumberCard(numberCard);
    }

    @Override
    public String getNumberCard() {
        return super.getNumberCard();
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
