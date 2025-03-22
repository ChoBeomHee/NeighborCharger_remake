package project.charger.domain.auth.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.charger.domain.user.entity.User;
import project.charger.domain.user.repository.UserRepository;

@Service
public class AuthService {
    final private UserRepository userRepository;
    final private BCryptPasswordEncoder encoder;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void join(User user) {
        if(userRepository.findByUsername(user.getUsername()).isEmpty()){
            String username = user.getUsername();
            String password = encoder.encode(user.getPassword());
            userRepository.save(new User(username, password));
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 등록된 회원");
        }
    }
}
