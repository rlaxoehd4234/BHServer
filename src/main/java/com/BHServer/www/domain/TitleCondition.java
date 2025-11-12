package com.BHServer.www.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "title_conditions",
        indexes = {
                @Index(name = "idx_conditiontype", columnList = "condition_type")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TitleCondition {

    @Id
    @Column(name = "title_code", length = 50, nullable = false)
    private String titleCode; // 칭호 내부 코드 (PK)

    @Column(name = "display_name", length = 100, nullable = false)
    private String displayName; // 칭호 표시 이름

    @Column(name = "description", length = 255, nullable = false)
    private String description; // 획득 조건 설명

    @Column(name = "condition_type", length = 30, nullable = false)
    private String conditionType; // 조건 유형 (예: BLOCKS_MINED 등)

    @Column(name = "required_value", nullable = false)
    private Long requiredValue; // 달성해야 할 수치

    @Column(name = "reward_data", length = 255)
    private String rewardData; // 보상 데이터 (JSON 등)

    @Column(name = "is_hidden", nullable = false)
    private boolean hidden = false; // 숨겨진 업적 여부 (BOOLEAN → tinyint(1))
}
