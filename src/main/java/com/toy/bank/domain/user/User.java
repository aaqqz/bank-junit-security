package com.toy.bank.domain.user;

import com.toy.bank.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tb_user")
@Entity
@NoArgsConstructor
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String userName;

    @Column(nullable = false, length = 60) // password encoding(BCrypt)
    private String password;

    @Column(nullable = false, length = 20)
    private String email;

    @Column(nullable = false, length = 20)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserEnum role;

    @Builder
    public User(Long id, String userName, String password, String email, String fullName, UserEnum role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
    }
}
