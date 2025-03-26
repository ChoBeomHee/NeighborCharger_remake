package project.charger.domain.user.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.charger.domain.user.Service.UserService;

@RestController
@RequestMapping("api/user")
public class userController {
    final private UserService userService;

    public userController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/check")
    public ResponseEntity<Integer> isUsernameAvailable(@RequestParam String username){
        boolean isAvailable = userService.isUsernameAvailable(username); // 서비스 레이어에서 중복 체크
        return isAvailable ? ResponseEntity.ok(1) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
    }
}
