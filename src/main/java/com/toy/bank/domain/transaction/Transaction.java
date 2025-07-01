package com.toy.bank.domain.transaction;

import com.toy.bank.domain.account.Account;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tb_transaction")
@Entity
@NoArgsConstructor
@Getter
public class Transaction {
    // 트랜잭션 엔티티는 거래 내역을 저장합니다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account withdrawAccount; // 출금 계좌
    private Long withdrawAccountBalance; // 출금 계좌 잔액

    @ManyToOne(fetch = FetchType.LAZY)
    private Account depositAccount; // 입금 계좌
    private Long depositAccountBalance; // 입금 계좌 잔액

    @Column(nullable = false)
    private Long account;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType; // 거래 유형 (출금, 입금, 이체, 내역조회)

    // 계좌가 사라져도 로그는 남아야 한다.(junit 테스트를 위해)
    private String sender;
    private String receiver;
    private String telephone;

    @Builder
    public Transaction(Long id, Account withdrawAccount, Long withdrawAccountBalance, Account depositAccount, Long depositAccountBalance, Long account, TransactionType transactionType, String sender, String receiver, String telephone) {
        this.withdrawAccount = withdrawAccount;
        this.withdrawAccountBalance = withdrawAccountBalance;
        this.depositAccount = depositAccount;
        this.depositAccountBalance = depositAccountBalance;
        this.account = account;
        this.transactionType = transactionType;
        this.sender = sender;
        this.receiver = receiver;
        this.telephone = telephone;
    }
}
