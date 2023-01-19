package com.grupo5.proyecto.controllers;

import com.grupo5.proyecto.entities.Centro;
import com.grupo5.proyecto.entities.Usuario;
import com.grupo5.proyecto.services.CentroService;
import com.grupo5.proyecto.services.EmailService;
import com.grupo5.proyecto.services.UsuarioService;
import com.grupo5.proyecto.services.implementations.UserDetailsServiceImpl;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

/**
 * RegistroController es la clase controladora del formulario de registro
 * Gestiona la visualización del formulario, de algunos campos mediante peticiones AJAX, de los mensajes de error
 * y el almacenamiento del usuario registrado
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */
@Controller
public class RegistroController {

    // Inyecciones de los servicios
    @Autowired
    private CentroService centroService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EmailService emailService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    // Método POST del registro (Con validación de datos de registro)
    @PostMapping("/registro")
    public String envioRegistro (HttpSession sesion, @Valid Usuario usuario, BindingResult result, String roles, Model model, RedirectAttributes redirAttrs) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), err.getDefaultMessage());
            });
            model.addAttribute("error", errores);
            return "registro";
        }


        if (usuarioService.existeEmail(usuario.getEmail())) {
            redirAttrs.addFlashAttribute("emailrepetido", true);
            return "redirect:/registro";
        } else if (usuarioService.existeUsername(usuario.getUsername())) {
            redirAttrs.addFlashAttribute("usernamerepetido", true);
            return "redirect:/registro";
        }

        if (roles != null) {
                    usuario.setPuntosTotales(0);
            switch (roles) {
                case "Estudiante":
                    usuario.setRol("ROLE_STUDENT");
                    usuario.setCuentaActiva(1);
                    redirAttrs.addFlashAttribute("registrado",true);
                    break;
                case "Docente":
                    usuario.setRol("ROLE_TEACHER");
                    usuario.setCuentaActiva(0);
                    redirAttrs.addFlashAttribute("profe_registrado",true);
                    break;
            }
        }
        usuarioService.guardar(usuario);
        return "redirect:/index";
    }

    // Método GET del registro. Carga de los selects con AJAX
    String nombreProvincia = "";
    String nombreMunicipio = "";
    @GetMapping({"/registro"})
    public String registro (Model model, Authentication auth){

        if (auth != null) {
            return userDetailsService.autoridadIndex(auth);
        }

        List<String> listaMunicipios = centroService.buscarPorProvincia(nombreProvincia);
        List<Centro> listaCentros = centroService.buscarPorMunicipio(nombreMunicipio);
        List<String> listaRoles = Arrays.asList("Estudiante", "Docente");
        List<String> listaSexo = Arrays.asList("Mujer", "Hombre", "Prefiero no decirlo");

        model.addAttribute("titulo","Registro de usuario");
        model.addAttribute("provincias",centroService.listarProvincias());
        model.addAttribute("municipios",listaMunicipios);
        model.addAttribute("centros",listaCentros);
        model.addAttribute("roles",listaRoles);
        model.addAttribute("sexo",listaSexo);

        return "registro";
    }

        // Lista de municipios segun la provincia elegida (AJAX)
        @RequestScope
        @GetMapping("/buscarPorProvincia")
        public @ResponseBody List<String> municipiosPorProvincias(
                @RequestParam(value = "provincia", required = true) String provincia){
            return centroService.buscarPorProvincia(provincia);
        }

        // Lista de tipos de centros segun el municipio elegido (AJAX)
        List<Centro> centrosMunicipio;
        @GetMapping("/buscarPorMunicipio")
        public @ResponseBody List<Centro> centrosPorMunicipio(
                @RequestParam(value = "municipio", required = true) String municipio){
            centrosMunicipio = centroService.buscarPorMunicipio(municipio);
            return centroService.listarTiposDeCentroEnMunicipio(centrosMunicipio);
        }

        // Lista de centros según el tipo de centro elegido en un municipio (AJAX)
        @GetMapping("/buscarPorTipo")
        public @ResponseBody List<Centro> centrosPorTipoDeCentro(@RequestParam(value = "municipio", required = true) String municipio,
                                                                 @RequestParam(value = "tipo", required = true) String tipo){
            return centroService.listarCentrosPorTipo(municipio, tipo);
        }
}