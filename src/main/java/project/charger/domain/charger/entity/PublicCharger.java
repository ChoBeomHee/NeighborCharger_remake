package project.charger.domain.charger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PublicCharger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int publicChargerId;
    private String metro;               // 광역시/도 (예: 서울특별시)
    private String city;                // 시/군/구 (예: 종로구)
    private String stationName;         // 충전소 명칭 (예: SK렌터카 본사)
    private String stationPullAddress;      // 충전소 주소
    private String rapidChargerCount;      // 급속 충전기 개수
    private String slowChargerCount;       // 완속 충전기 개수
    private String supportedCarTypes; // 지원 차종 (예: ["SM3 Z.E", "테슬라"])
}
