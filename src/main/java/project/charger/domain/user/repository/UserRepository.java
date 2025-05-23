package project.charger.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.charger.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
