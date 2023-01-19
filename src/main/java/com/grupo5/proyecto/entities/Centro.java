package com.grupo5.proyecto.entities;

import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Centro es una entidad que mapea a la tabla "centros" de la base de datos
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */
@Entity
@Table(name = "centros")
public class Centro implements Serializable{

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcentros")
    private int id;

    private String tipo;

    private String nombre;

    private String domicilio;

    private String titularidad;

    private String localidad;

    private String provincia;

    private String municipio;

    // Constructor
    public Centro() {
    }

    // Constructores
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTitularidad() {
        return titularidad;
    }

    public void setTitularidad(String titularidad) {
        this.titularidad = titularidad;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
}
