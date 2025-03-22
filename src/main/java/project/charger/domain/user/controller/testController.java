package project.charger.domain.user.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @GetMapping("/")
    public String index(Authentication authentication){
        return "로그인에 성공하였어요. : " + authentication.getName();
    }
}
