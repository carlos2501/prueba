package com.grupo5.proyecto.services;

import com.grupo5.proyecto.entities.Partida;
import com.grupo5.proyecto.entities.Usuario;

import java.util.List;

public interface IfxPartidaSrvc {
    List<Partida> finAll();
    void nuevaPartida(Partida partida);
    Partida iniciaPartida(Partida partida, List<Usuario> jugadores, Integer tiempo);
    Partida terminaPartida(Partida partida, Usuario usuario, Integer puntos);
}
