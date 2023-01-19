package com.grupo5.proyecto.repositories;

import com.grupo5.proyecto.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UsuarioRepository utiliza Hibernate para realizar consultas sobre el usuario a la base de datos.
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    @Transactional(readOnly = true)
    @Query("select u from Usuario u where u.curso = ?1 and u.centro = ?2 and u.rol = 'ROLE_STUDENT'")
    List<Usuario> listaAlumnos(Integer curso, Integer centro);

    @Transactional
    Usuario findByEmail(String email);

    @Transactional
    Usuario findByUsername(String username);

    @Transactional(readOnly = true)
    @Query("select count(u) from Usuario u WHERE u.username = ?1")
    Integer countByUsername(String username);

    @Transactional(readOnly = true)
    @Query("select count(u) from Usuario u WHERE u.email = ?1")
    Integer countByEmail(String email);

    @Transactional
    @Query("select u.intentosLogueo from Usuario u where u.username = ?1")
    String findIntentosLogueoByUsername(String username);

}
