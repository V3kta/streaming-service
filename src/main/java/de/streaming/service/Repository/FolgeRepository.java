package de.streaming.service.Repository;

import de.streaming.service.Entity.Folge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolgeRepository extends JpaRepository<Folge, Integer> {
}
