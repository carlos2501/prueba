package com.grupo5.proyecto.services.implementations;

import com.grupo5.proyecto.entities.Grupo;
import com.grupo5.proyecto.repositories.GrupoRepository;
import com.grupo5.proyecto.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GrupoServiceImpl implements GrupoService {

    @Autowired
    GrupoRepository repoGrupo;

    @Override
    public List<Grupo> listaGruposPorCentro(Long idcentro) {
        return repoGrupo.findGruposByCentro(idcentro);
    }
}
