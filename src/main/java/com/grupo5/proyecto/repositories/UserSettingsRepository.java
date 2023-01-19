package com.grupo5.proyecto.repositories;

import com.grupo5.proyecto.entities.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSettingsRepository extends JpaRepository<UserSettings, Integer> {
      List<UserSettings> findByNombre(String nombre);

}
