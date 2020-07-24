package de.streaming.service.Repository;

import de.streaming.service.Entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Integer> {
}
