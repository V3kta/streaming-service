package de.streaming.service.Repository;

import de.streaming.service.Entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Integer> {
    Settings findByUser_Id(Integer userId);
    Boolean existsByUser_Id(Integer userId);
}
