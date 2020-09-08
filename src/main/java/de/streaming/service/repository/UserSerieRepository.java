package de.streaming.service.repository;

import de.streaming.service.entity.Serie;
import de.streaming.service.entity.User;
import de.streaming.service.entity.UserSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSerieRepository extends JpaRepository<UserSerie, Integer> {

    List<UserSerie> findBySerieId(Integer id);

    List<UserSerie> findByUserId(Integer id);

    void removeBySerieAndUser(Serie serie, User user);
}
