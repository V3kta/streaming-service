package de.streaming.service.Repository;

import de.streaming.service.Entity.Serie;
import de.streaming.service.Entity.User;
import de.streaming.service.Entity.UserSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserSerieRepository extends JpaRepository<UserSerie, Integer> {

    List<UserSerie> findBySerieId(Integer id);

    List<UserSerie> findByUserId(Integer id);

}
