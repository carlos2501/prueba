package com.grupo5.proyecto.entities;

import javax.persistence.*;

@Entity
@Table(name = "trivial")
public class Trivial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpreguntas")
    private int id;

    private String pregunta;
    private String opciona;
    private String opcionb;
    private String opcionc;

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    private String respuesta;


    public Trivial() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getOpciona() {
        return opciona;
    }

    public void setOpciona(String opciona) {
        this.opciona = opciona;
    }

    public String getOpcionb() {
        return opcionb;
    }

    public void setOpcionb(String opcionb) {
        this.opcionb = opcionb;
    }

    public String getOpcionc() {
        return opcionc;
    }

    public void setOpcionc(String opcionc) {
        this.opcionc = opcionc;
    }
}
