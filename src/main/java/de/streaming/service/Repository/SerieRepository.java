package de.streaming.service.Repository;

import de.streaming.service.Entity.Serie;
import de.streaming.service.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Integer> {
    List<Serie> findByUsers(User user);
}
