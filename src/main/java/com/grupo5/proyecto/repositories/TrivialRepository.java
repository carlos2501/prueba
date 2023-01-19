package com.grupo5.proyecto.repositories;

import com.grupo5.proyecto.entities.Trivial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrivialRepository extends JpaRepository<Trivial, Integer> {
}
