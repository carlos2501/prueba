package com.grupo5.proyecto.repositories;

import com.grupo5.proyecto.entities.Grupo;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    @Transactional(readOnly = true)
    @Query("select g from Grupo g where g.idCentro = ?1")
    List<Grupo> findGruposByCentro(Long idcentro);
}
