package com.grupo5.proyecto.services.implementations;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * MyUserDetails es la implementación de la interfaz UserDetails de Spring Security
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */
public class MyUserDetails implements UserDetails {

    private int id;
    private String username;
    private String password;
    private String rol;
    private int cuentaActiva;

    public MyUserDetails(Integer id, String username, String password, String rol, int cuentaActiva) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.cuentaActiva = cuentaActiva;
    }

    public MyUserDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.rol);
        authorities.add(authority);
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return (cuentaActiva==1)?true:false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
