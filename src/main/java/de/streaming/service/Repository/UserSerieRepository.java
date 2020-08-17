package de.streaming.service.Repository;

import de.streaming.service.Entity.Serie;
import de.streaming.service.Entity.User;
import de.streaming.service.Entity.UserSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSerieRepository extends JpaRepository<UserSerie, Integer> {

    List<UserSerie> findBySerieId(Integer id);

    List<UserSerie> findByUserId(Integer id);

    void removeBySerieAndUser(Serie serie, User user);
}
