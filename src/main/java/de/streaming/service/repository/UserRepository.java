package de.streaming.service.repository;

import de.streaming.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);
    User findByEmailAndPassword(String email, String password);
    User findByIdAndPassword(Integer id, String password);
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByPassword(String password);
    Boolean existsByIdAndPassword(Integer id, String password);
}
