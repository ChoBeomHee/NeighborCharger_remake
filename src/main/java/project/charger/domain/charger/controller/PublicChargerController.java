package project.charger.domain.charger.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.charger.domain.charger.entity.Dto.PublicChargerListResDto;
import project.charger.domain.charger.service.PublicChargerService;

import java.util.List;

@RestController
@RequestMapping("api/charger")
public class PublicChargerController {
    final private PublicChargerService publicChargerService;

    public PublicChargerController(PublicChargerService publicChargerService) {
        this.publicChargerService = publicChargerService;
    }

    /**
     * 공용 전기차 목록을 리턴하는 API
     * @return List<publicChargerListResDto>
     */
    @GetMapping("/publicChargerList")
    ResponseEntity<List<PublicChargerListResDto>> publicChargerListInq(){
        return new ResponseEntity<>(publicChargerService.getPublicChargerList(), HttpStatus.OK);
    }
}
