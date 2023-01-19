package com.grupo5.proyecto.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpartida", nullable = false)
    private Long idPartida;
    private Integer duracion;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Usuario> jugadores = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "ganador_idusuario")
    private Usuario ganador;
    @ManyToOne
    @JoinColumn(name = "profesor_idusuario")
    private Usuario profesor;

    public Partida() {
    }

    public Partida(Integer duracion, Collection<Usuario> jugadores, Usuario profesor) {
        this.duracion = duracion;
        this.jugadores = jugadores;
        this.profesor = profesor;
    }

    public Long getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Long idPartida) {
        this.idPartida = idPartida;
    }

    public Usuario getProfesor() {
        return profesor;
    }

    public void setProfesor(Usuario profesor) {
        this.profesor = profesor;
    }

    public Usuario getGanador() {
        return ganador;
    }

    public void setGanador(Usuario ganador) {
        this.ganador = ganador;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Collection<Usuario> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Collection<Usuario> jugadores) {
        this.jugadores = jugadores;
    }
}
