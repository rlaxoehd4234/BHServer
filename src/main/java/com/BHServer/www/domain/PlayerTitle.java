package com.BHServer.www.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "player_titles",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_player_title", columnNames = {"uuid", "title_code"})
        },
        indexes = {
                @Index(name = "idx_active_uuid", columnList = "uuid, is_active")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id; // AUTO_INCREMENT

    @Column(name = "uuid", length = 36, nullable = false)
    private String uuid; // 플레이어 UUID

    @Column(name = "title_code", length = 50, nullable = false)
    private String titleCode; // 칭호 코드

    @Column(name = "title_display_name", length = 100, nullable = false)
    private String titleDisplayName; // 칭호 표시 이름

    @Column(name = "is_active", nullable = false)
    private boolean active = false; // 현재 활성화 상태

    @Column(
            name = "date_obtained",
            nullable = false,
            columnDefinition = "timestamp not null default current_timestamp"
    )
    private LocalDateTime dateObtained; // 획득 시각

    @Column(name = "is_permanent", nullable = false)
    private boolean permanent = true; // 영구 칭호 여부
}
