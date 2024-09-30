package com.mini_autorizador.repository.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cards", uniqueConstraints = @UniqueConstraint(columnNames = "number"))
public class CardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false, length = 100)
    private String numberCard;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Column(name = "balance", nullable = false, length = 200)
    private BigDecimal balance;

    public CardModel() {
    }

    public CardModel(Long id, String numberCard, String password, BigDecimal balance) {
        this.id = id;
        this.numberCard = numberCard;
        this.password = password;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
