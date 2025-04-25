package project.charger.domain.charger.service;

import org.springframework.stereotype.Service;
import project.charger.domain.charger.Repository.PublicChargerRepository;
import project.charger.domain.charger.entity.Dto.PublicChargerListResDto;
import project.charger.domain.charger.entity.PublicCharger;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicChargerService {
    final private PublicChargerRepository publicChargerRepository;

    public PublicChargerService(PublicChargerRepository publicChargerRepository) {
        this.publicChargerRepository = publicChargerRepository;
    }

    public List<PublicChargerListResDto> getPublicChargerList(){
        List<PublicCharger> publicChargerList = publicChargerRepository.findAll();
        List<PublicChargerListResDto> publicChargerListResDtoList = new ArrayList<>();

        for(PublicCharger p : publicChargerList){
            publicChargerListResDtoList.add(PublicChargerListResDto.builder()
                    .metro(p.getMetro())
                    .city(p.getCity())
                    .stationName(p.getStationName())
                    .stationAddress(p.getStationAddress())
                    .rapidChargerCount(p.getRapidChargerCount())
                    .slowChargerCount(p.getSlowChargerCount())
                    .supportedCarTypes(p.getSupportedCarTypes())
                    .build());
        }

        return publicChargerListResDtoList;
    }
}
