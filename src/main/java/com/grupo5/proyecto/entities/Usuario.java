package com.grupo5.proyecto.entities;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Usuario es una entidad que mapea a la tabla "usuarios" de la base de datos
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private int idUsuario;

    @NotEmpty(message = "Necesitas un nombre de usuario para poder registrarte")
    private String username;

    @NotEmpty(message = "Necesitas una contraseña para poder registrarte")
    @Size(min = 3, message="La contraseña debe tener más de 8 caracteres")
    private String password;

    @NotEmpty(message = "Debes escribir tu nombre")
    private String nombre;

    @NotEmpty(message = "Debes escribir tu primer apellido")
    @Column(name = "primer_apellido")
    private String primerApellido;

    @NotEmpty(message = "Debes escribir tu segundo apellido")
    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @NotEmpty(message = "Debes escribir tu email")
    @Email(message = "Debes escribir un email válido")
    private String email;

    private String rol;

    @Column(name = "fecha_nacimiento")
    @NotNull(message = "Debes escribir tu fecha de nacimiento")
    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @Column(name = "intentos_logueo")
    private Integer intentosLogueo;

    @Column(name = "ultimo_intento_logueo")
    private LocalDateTime ultimoIntentoLogueo;

    @Column(name = "puntos_total_jugador")
    private Integer puntosTotales;

    @Column(name = "cuenta_activa")
    private Integer cuentaActiva;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuarios")
    private List<Grupo> grupos = new ArrayList<Grupo>();

    // Constructores
    public Usuario() {
    }

    // Getters y setters

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

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

    public Integer getIntentosLogueo() {
        return intentosLogueo;
    }
    public void setIntentosLogueo(Integer intentosLogueo) {
        this.intentosLogueo = intentosLogueo;
    }

    public LocalDateTime getUltimoIntentoLogueo() {
        return ultimoIntentoLogueo;
    }
    public void setUltimoIntentoLogueo(LocalDateTime ultimoIntentoLogueo) {
        this.ultimoIntentoLogueo = ultimoIntentoLogueo;
    }

    public Integer getPuntosTotales() {
        return puntosTotales;
    }
    public void setPuntosTotales(Integer puntosTotales) {
        this.puntosTotales = puntosTotales;
    }

    public Integer getCuentaActiva() {
        return cuentaActiva;
    }
    public void setCuentaActiva(Integer cuentaActiva) {
        this.cuentaActiva = cuentaActiva;
    }

    public List<Grupo> getGrupos() { return grupos; }
    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    // Método toString
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + idUsuario +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", email='" + email + '\'' +
                ", rol='" + rol + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", intentosLogueo=" + intentosLogueo +
                ", ultimoIntentoLogueo=" + ultimoIntentoLogueo +
                ", puntosTotales=" + puntosTotales +
                ", cuentaActiva=" + cuentaActiva +
                '}';
    }
}
