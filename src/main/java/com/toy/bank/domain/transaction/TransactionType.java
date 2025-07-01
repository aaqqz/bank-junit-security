package com.toy.bank.domain.transaction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionType {
    WITHDRAW("출금"),
    DEPOSIT("입금"),
    TRANSFER("이체"),
    ALL("입출금내역");

    private final String value;
}
