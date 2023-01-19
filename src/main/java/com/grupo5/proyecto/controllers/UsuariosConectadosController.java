package com.grupo5.proyecto.controllers;

import com.grupo5.proyecto.entities.Usuario;
import com.grupo5.proyecto.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UsuariosConectadosController {
    @Autowired
    SessionRegistry sessionRegistry;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/usuariosconectados")
    public String usuariosConectados(Model model) {
        // Listamos los usuarios logueados por consola (Para poder depurar errores)
        encuentraUsuariosLogueados().forEach(System.out::println);
        // Enviamos los usuarios a la vista
        model.addAttribute("usuariosconectados",encuentraUsuariosLogueados());
        return "usuariosconectados";
    }


    /**
     * El método encuentraUsuariosLogueados devuelve la lista con los Usuarios logueados
     */
    public List<Usuario> encuentraUsuariosLogueados() {
        return sessionRegistry.getAllPrincipals()
                .stream()
                .filter(principal -> principal instanceof UserDetails)
                .map(UserDetails.class::cast)
                .map(UserDetails::getUsername)
                .map(usuarioRepository::findByUsername)
                .collect(Collectors.toList());
    }
}
