package de.streaming.service.Repository;

import de.streaming.service.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameOrEmailAndPassword(String username, String email, String password);
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
