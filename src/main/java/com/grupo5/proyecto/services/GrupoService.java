package com.grupo5.proyecto.services;

import com.grupo5.proyecto.entities.Grupo;

import java.util.List;

public interface GrupoService {
    List<Grupo> listaGruposPorCentro(Long idcentro);
}
