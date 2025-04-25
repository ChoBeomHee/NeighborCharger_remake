package project.charger.domain.charger.entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PublicChargerListResDto {
    private String metro;               // 광역시/도 (예: 서울특별시)
    private String city;                // 시/군/구 (예: 종로구)
    private String stationName;         // 충전소 명칭 (예: SK렌터카 본사)
    private String stationAddress;      // 충전소 주소
    private String rapidChargerCount;      // 급속 충전기 개수
    private String slowChargerCount;       // 완속 충전기 개수
    private String supportedCarTypes; // 지원 차종 (예: ["SM3 Z.E", "테슬라"])

}
