package de.streaming.service.Repository;

import de.streaming.service.Entity.Serie;
import de.streaming.service.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
