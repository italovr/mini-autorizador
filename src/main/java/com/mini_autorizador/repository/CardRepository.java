package com.mini_autorizador.repository;

import com.mini_autorizador.repository.model.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardModel, Long> {

    Optional<CardModel> findByNumberCard(String numberCard);

}

