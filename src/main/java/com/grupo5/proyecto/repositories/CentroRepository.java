package com.grupo5.proyecto.repositories;

import com.grupo5.proyecto.entities.Centro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * CentroRepository utiliza Hibernate para realizar consultas sobre el centro a la base de datos.
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */
@Repository
public interface CentroRepository extends JpaRepository<Centro,Long> {

    // Lista  de provincias de los centros
    @Transactional(readOnly = true)
    @Query("select distinct c.provincia from Centro c order by c.provincia")
    List<String> listProvincias();

    // Lista municipios por provincia
    @Transactional(readOnly = true)
    @Query("select distinct c.municipio from Centro c where c.provincia = ?1 order by c.municipio")
    List<String> findByProvincia(String provincia);

    // Lista centros por municipio
    @Transactional(readOnly = true)
    @Query("select c from Centro c where c.municipio = ?1 order by c.nombre")
    List<Centro> findByMunicipio(String municipio);

    @Transactional(readOnly = true)
    @Query("select c from Centro c where c.municipio = ?1 and c.tipo = ?2 order by c.nombre")
    List<Centro> findByMunicipioAndTipo(String municipio, String tipo);
}
