package com.BHServer.www.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionId;

    @Column(nullable = false)
    private String auctionName;

    private UUID sellerId;

    private UUID winner;

    @Column(nullable = false)
    private Long startPrice;

    @Column(nullable = false)
    private Long bidIncrement;  // ¿‘¬˚ ¥‹¿ß

    @Column(nullable = false)
    private Long currentPrice;

    @Embedded
    private Category category;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

}