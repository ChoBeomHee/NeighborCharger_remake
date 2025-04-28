package project.charger;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.charger.domain.charger.service.PublicChargerService;

@RestController
public class testController {
    final private PublicChargerService publicChargerService;

    public testController(PublicChargerService publicChargerService) {
        this.publicChargerService = publicChargerService;
    }

    @GetMapping("/")
    public String index(Authentication authentication){
        return "로그인에 성공하였어요. : " + authentication.getName();
    }

    @GetMapping("/test")
    public void test(){
        //publicChargerService.updatePublicChargerList();
    }
}
