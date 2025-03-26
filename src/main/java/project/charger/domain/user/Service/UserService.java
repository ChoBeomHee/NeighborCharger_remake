package project.charger.domain.user.Service;

import org.springframework.stereotype.Service;
import project.charger.domain.user.entity.User;
import project.charger.domain.user.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    final private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUsernameAvailable(String username){
        Optional<User> users = this.userRepository.findByUsername(username);
        return users.isPresent();
    }
}
