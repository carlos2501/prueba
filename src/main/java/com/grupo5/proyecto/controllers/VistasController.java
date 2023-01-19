package com.grupo5.proyecto.controllers;

import com.grupo5.proyecto.dao.EmailDetalles;
import com.grupo5.proyecto.entities.Usuario;
import com.grupo5.proyecto.repositories.UsuarioRepository;
import com.grupo5.proyecto.services.EmailService;
import com.grupo5.proyecto.services.UsuarioService;
import com.grupo5.proyecto.services.implementations.UserDetailsServiceImpl;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * VistasController es la clase controladora de las vistas de la aplicación web (exceptuando el registro y los ajustes)
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */
@Controller
public class VistasController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    EmailService emailService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping({"", "/", "/index", "/home"})
    public String index(Model model, Authentication auth) {
        return userDetailsService.autoridadIndex(auth);
    }

    @GetMapping("/pruebacheckbox")
    public String listaCheckbox(Model model) {
        model.addAttribute("colores",Arrays.asList("rojo","amarillo","azul","verde","rosa","violeta"));
//        model.addAttribute("seleccionado", new ArrayList());
        return "pruebacheckbox";
    }

    @PostMapping("/cajabox")
    public String envioCheckbox(Model model, @RequestBody ArrayList<String> seleccionado) {
        seleccionado.forEach(System.out::println);
//        System.out.println(seleccionado);
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model, Authentication auth) {
        return userDetailsService.autoridadAcceso(auth, "login");
    }


    @GetMapping("/admin")
    public String admin(Model model, Authentication auth) {

        model.addAttribute("nombre", auth.getName());
        return "indexadmin";
    }

    @GetMapping("/docente")
    public String docente(Model model, Authentication auth) {
        model.addAttribute("nombre", auth.getName());
        return "indexdocente";
    }

    @GetMapping("/estudiante")
    public String estudiante(Model model, Authentication auth) {
        model.addAttribute("nombre", auth.getName());
        return "indexestudiante";
    }

    @GetMapping("/juegos")
    public String juegos(Model model, Authentication auth) {
        model.addAttribute("profesor", auth.getDetails() );
        return "juegos";
    }

    @GetMapping("/contacto")
    public String contacto(Model model) {
        return "contacto";
    }

    @GetMapping("/creaciongrupos")
    public String creacionGrupos(Model model, Authentication auth) {
        Usuario profe = usuarioRepository.findByUsername(auth.getName());
        List<Usuario> alumnos = usuarioRepository.listaAlumnos(Integer.valueOf(profe.getCurso()), profe.getCentro().getId());
        return "creaciongrupos";
    }

    @GetMapping("/forgot")
    public String olvidePassword(Model model) {
        return "olvide";
    }

    @PostMapping("/forgot")
    public String olvidePasswordEnvio(Model model, @RequestParam String email) {
        if (usuarioService.existeEmail(email)) {
            System.out.println(emailService.enviarEmailSimple(new EmailDetalles(email, "Recuperación de contraseña", usuarioService.mensajeForgotPassword(email))));
        }
        // Tarea pendiente: Incluir en el email enlace para modificar contraseña
        model.addAttribute("forgot",true);
        return "login";
    }
}