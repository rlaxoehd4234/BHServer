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
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private UUID sender;  // null 가능 (시스템 알림의 경우)

    private UUID receiver;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private NotificationType type;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "is_read", nullable = false)
    private boolean read = false;

    @Column(name = "is_sent", nullable = false)
    private boolean sent = false;

    @Column(length = 255)
    private String relatedUrl;  // 클릭 시 이동할 URL

    private Long relatedEntityId;  // 관련 엔티티 ID (예: auctionId)

    private LocalDateTime readAt;  // 읽은 시간

    private LocalDateTime sentAt;  // 발송된 시간

}
