package project.charger.domain.charger.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import project.charger.domain.charger.Repository.PublicChargerRepository;
import project.charger.domain.charger.entity.Dto.PublicChargerListResDto;
import project.charger.domain.charger.entity.PublicCharger;

import java.io.IOException;
import java.util.*;

@Service
public class PublicChargerService {
    final private PublicChargerRepository publicChargerRepository;

    public PublicChargerService(PublicChargerRepository publicChargerRepository) {
        this.publicChargerRepository = publicChargerRepository;
    }

    /**
     * DB에 저장되어 있는 공용충전기의 목록을 반환하는 메소드
     * @return 공용충전기 목록
     */
    public List<PublicChargerListResDto> getPublicChargerList(){
        List<PublicCharger> publicChargerList = publicChargerRepository.findAll();
        List<PublicChargerListResDto> publicChargerListResDtoList = new ArrayList<>();

        for(PublicCharger p : publicChargerList){
            publicChargerListResDtoList.add(PublicChargerListResDto.builder()
                    .metro(p.getMetro())
                    .city(p.getCity())
                    .stationName(p.getStationName())
                    .stationAddress(p.getStationPullAddress())
                    .rapidChargerCount(p.getRapidChargerCount())
                    .slowChargerCount(p.getSlowChargerCount())
                    .supportedCarTypes(p.getSupportedCarTypes())
                    .build());
        }

        return publicChargerListResDtoList;
    }

    /**
     * 배치에서 공공 API 호출하여 DB에 저장하는 메소드
     */
    public boolean updatePublicChargerList() throws IOException {
        RestClient restClient = RestClient.create();

        ResponseEntity<String> result = restClient.get()
                .uri("https://bigdata.kepco.co.kr/openapi/v1/EVcharge.do?metroCd=11&city=1&apiKey=E40mvFp2GzZXHAOmci4Q5U7TZnEkBM8wONFK38Iu&returnType=json")
                .retrieve()
                .toEntity(String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode data = mapper.readTree(result.getBody());
        JsonNode arr = data.get("data");

        if (!publicChargerRepository.findAll().isEmpty()) {
            publicChargerRepository.deleteAll();
        }
        try {
            for (JsonNode info : arr) {
                publicChargerRepository.save(PublicCharger.builder()
                        .metro(String.valueOf(info.get("metro")).replaceAll("^\"+|\"+$", ""))
                        .city(String.valueOf(info.get("city")).replaceAll("^\"+|\"+$", ""))
                        .stationName(String.valueOf(info.get("stnAddr")).replaceAll("^\"+|\"+$", ""))
                        .stationPullAddress(
                                String.valueOf(info.get("metro")).replaceAll("^\"+|\"+$", "")
                                        + " "
                                        + String.valueOf(info.get("city")).replaceAll("^\"+|\"+$", "")
                                        + " "
                                        + String.valueOf(info.get("stnAddr")).replaceAll("^\"+|\"+$", "")
                        )
                        .slowChargerCount(String.valueOf(info.get("slowCnt")))
                        .rapidChargerCount(String.valueOf(info.get("rapidCnt")))
                        .supportedCarTypes(String.valueOf(info.get("carType")).replaceAll("^\"+|\"+$", ""))
                        .build());
            }
        } catch (Exception e) {
            throw new IOException("오픈 API 중 오류 발생");
        }

        return true;
    }
}
