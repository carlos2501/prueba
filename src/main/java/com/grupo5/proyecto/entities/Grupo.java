package com.grupo5.proyecto.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="grupos")
public class Grupo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgrupo", nullable = false)
    private Long idGrupo;
    @ManyToOne
    @JoinColumn(name = "idcentro")
    private Centro centro;
    private String grupo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="usuarios_grupos",
    joinColumns = {@JoinColumn(name="idGrupo")},
    inverseJoinColumns = {@JoinColumn(name="idUsuario")})
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    public Long getIdGrupo() { return idGrupo; }
    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Centro getCentro() { return centro; }
    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public String getCurso() { return grupo; }
    public void setCurso(String curso) {
        this.grupo = curso;
    }

    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public List<Usuario> getUsuarios() { return usuarios; }
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}

