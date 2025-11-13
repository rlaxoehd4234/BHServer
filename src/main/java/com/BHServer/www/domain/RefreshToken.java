package com.BHServer.www.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Long expiry;

    public void updateToken(String newToken, Long newExpiry) {
        this.token = newToken;
        this.expiry = newExpiry;
    }
}
