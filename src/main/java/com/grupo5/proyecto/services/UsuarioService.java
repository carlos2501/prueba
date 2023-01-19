package com.grupo5.proyecto.services;

//import com.grupo5.proyecto.dao.UsuarioDao;
import com.grupo5.proyecto.entities.Usuario;
//import org.springframework.security.core.GrantedAuthority;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.Collection;

/**
 * UsuarioService define las firmas de los métodos de servicio para la clase Usuario
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */
public interface UsuarioService {
    public boolean existeEmail (String email);
    public boolean existeUsername (String username);
    public void guardar(Usuario usuario);
    public String intentosLogueo(String username);
    public Usuario encontrarPorEmail(String email);
    public String mensajeForgotPassword (String email);
    public Usuario encontrarPorUsername(String username);
    }
