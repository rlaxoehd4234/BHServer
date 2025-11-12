package com.BHServer.www.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "player_stats")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerStats {

    @Id
    @Column(name = "uuid", length = 36, nullable = false)
    private String uuid; // VARCHAR(36) → 간단히 String으로 매핑 (UUID로 쓰려면 Converter 추가)

    @Column(name = "player_name", length = 16, nullable = false)
    private String playerName;

    // DB의 DEFAULT CURRENT_TIMESTAMP를 그대로 쓰고 싶으면 columnDefinition 지정 (DDL 생성 시 반영)
    @Column(
            name = "last_login",
            nullable = false,
            columnDefinition = "timestamp not null default current_timestamp"
    )
    private java.time.LocalDateTime lastLogin;

    @Column(name = "total_play_time", nullable = false)
    private Long totalPlayTime;

    @Column(name = "blocks_mined", nullable = false)
    private Long blocksMined;

    @Column(name = "blocks_placed", nullable = false)
    private Long blocksPlaced;

    @Column(name = "player_kills", nullable = false)
    private Integer playerKills;

    @Column(name = "mob_kills", nullable = false)
    private Integer mobKills;

    @Column(name = "deaths", nullable = false)
    private Integer deaths;

    @Column(name = "distance_traveled", nullable = false)
    private Long distanceTraveled;

    @Column(name = "jumps", nullable = false)
    private Integer jumps;

    @Column(name = "items_crafted", nullable = false)
    private Integer itemsCrafted;

    @Column(name = "damage_dealt", nullable = false)
    private Long damageDealt;

    @Column(name = "damage_taken", nullable = false)
    private Long damageTaken;

    // ON UPDATE CURRENT_TIMESTAMP를 DB가 처리하도록 columnDefinition 지정
    @Column(
            name = "last_update",
            nullable = false,
            columnDefinition = "timestamp not null default current_timestamp on update current_timestamp"
    )
    private java.time.LocalDateTime lastUpdate;

//    public static PlayerStats of(String uuid, String playerName) {
//        return PlayerStats.builder()
//                .uuid(uuid)
//                .playerName(playerName)
//                .totalPlayTime(0L)
//                .blocksMined(0L)
//                .blocksPlaced(0L)
//                .playerKills(0)
//                .mobKills(0)
//                .deaths(0)
//                .distanceTraveled(0L)
//                .jumps(0)
//                .itemsCrafted(0)
//                .damageDealt(0L)
//                .damageTaken(0L)
//                .build();
//    }
}
