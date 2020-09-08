package de.streaming.service.repository;

import de.streaming.service.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Integer> {
    Settings findByUser_Id(Integer userId);
    Boolean existsByUser_Id(Integer userId);
}
