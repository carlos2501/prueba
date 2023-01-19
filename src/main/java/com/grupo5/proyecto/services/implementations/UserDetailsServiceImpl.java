package com.grupo5.proyecto.services.implementations;

import com.grupo5.proyecto.dao.EmailDetalles;
import com.grupo5.proyecto.entities.Usuario;
import com.grupo5.proyecto.repositories.UsuarioRepository;
import com.grupo5.proyecto.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserDetailsServiceImpl es la implementación de la interfaz UserDetailsService de Spring Security
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userRepository.findByUsername(username);
        return new MyUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getRol(), user.getCuentaActiva());
    }

    public String autoridadIndex (Authentication auth) {
        if (auth != null) {
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                    return "indexadmin";
                } else if (grantedAuthority.getAuthority().equals("ROLE_TEACHER")) {
                    return "indexdocente";
                } else if (grantedAuthority.getAuthority().equals("ROLE_STUDENT")) {
                    return "indexestudiante";
                }
            }
        }
        return "index";
    }

    public String autoridadAcceso (Authentication auth, String direccion) {
        if (auth != null) {
            return autoridadIndex(auth);
            }
        return direccion;
    }
}
