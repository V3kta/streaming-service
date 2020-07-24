package de.streaming.service.Repository;

import de.streaming.service.Entity.Staffel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffelRepository extends JpaRepository<Staffel, Integer> {
}
