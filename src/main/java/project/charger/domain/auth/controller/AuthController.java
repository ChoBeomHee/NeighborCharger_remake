package project.charger.domain.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.charger.domain.auth.service.AuthService;
import project.charger.domain.user.entity.User;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    final private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/join")
    public void join(@RequestBody User user) throws Exception {
        authService.join(user);
    }
}
