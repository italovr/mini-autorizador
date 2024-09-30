package com.mini_autorizador.util;

import com.mini_autorizador.bo.Transaction;
import com.mini_autorizador.controller.dto.TransactionRequest;

public class TransactionMapper {

    public static Transaction toTransaction(TransactionRequest transactionRequest) {
        return new Transaction(transactionRequest.getValue(), transactionRequest.getNumberCard(), transactionRequest.getPassword());
    }

}
