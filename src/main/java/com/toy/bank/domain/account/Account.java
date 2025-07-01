package com.toy.bank.domain.account;

import com.toy.bank.common.entity.BaseEntity;
import com.toy.bank.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tb_account")
@Entity
@NoArgsConstructor
@Getter
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private Long number; // 계좌번호

    @Column(nullable = false, length = 4)
    private Long password; // 계좌 비밀번호

    @Column(nullable = false)
    private Long balance; // 잔액 (기본값 1000원)

    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // fk user_id

    @Builder
    public Account(Long id, Long number, Long password, Long balance, User user) {
        this.number = number;
        this.password = password;
        this.balance = balance;
        this.user = user;
    }
}
