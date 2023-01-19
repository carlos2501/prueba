package com.grupo5.proyecto.services.implementations;

import com.grupo5.proyecto.dao.IfxPartidaDao;
import com.grupo5.proyecto.entities.Partida;
import com.grupo5.proyecto.entities.Usuario;
import com.grupo5.proyecto.repositories.UsuarioRepository;
import com.grupo5.proyecto.services.IfxPartidaSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
public class PartidaSrvcImpl implements IfxPartidaSrvc {
    @Autowired
    private UsuarioRepository usuRepo;

    @Autowired
    private IfxPartidaDao partidaDao;

    @Override

    public List<Partida> finAll() {
        return (List<Partida>) partidaDao.findAll();
    }

    @Override

    public void nuevaPartida(Partida partida) {
        partidaDao.save(partida);
    }

    @Override
    public Partida iniciaPartida(Partida partida, List<Usuario> jugadores, Integer tiempo) {
        return null;
    }

    @Override
    public Partida terminaPartida(Partida partida, Usuario usuario, Integer puntos) {
        return null;
    }
}
