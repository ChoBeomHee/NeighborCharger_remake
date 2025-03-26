package project.charger.domain.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.charger.domain.user.entity.User;
import project.charger.domain.user.repository.UserRepository;

@Slf4j
@Service
public class PrincipalDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public PrincipalDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()->{
            return new UsernameNotFoundException("아이디 혹은 비밀번호가 틀립니다. : " + username);
        });

        return new PrincipalDetail(user);
    }
}
