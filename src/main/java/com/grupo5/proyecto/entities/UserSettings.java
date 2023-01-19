package com.grupo5.proyecto.entities;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
public class UserSettings implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuarios")
    private int id;

    private String username;
    private String nombre;

    @Column(name = "primer_apellido")
    private String primerApellido;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    private String email;
    private String password;
    private String curso;
    private String rol;

    @Column(name = "fecha_nacimiento")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    private int cuenta_activa;


    @Column(name = "puntos_total_jugador")
    private int puntosJug;

    private int centro_idcentros;





    // Constructores
    public UserSettings() {
    }

    public UserSettings(String username, String password, String nombre, String primerApellido, String segundoApellido
            , String email) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.email = email;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getCuenta_activa() {
        return cuenta_activa;
    }

    public void setCuenta_activa(int cuenta_activa) {
        this.cuenta_activa = cuenta_activa;
    }

    public int getPuntosJug() {
        return puntosJug;
    }

    public void setPuntosJug(int puntosJug) {
        this.puntosJug = puntosJug;
    }

    public int getCentro_idcentros() {
        return centro_idcentros;
    }

    public void setCentro_idcentros(int centro_idcentros) {
        this.centro_idcentros = centro_idcentros;
    }
}

