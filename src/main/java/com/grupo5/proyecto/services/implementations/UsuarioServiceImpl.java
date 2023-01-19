package com.grupo5.proyecto.services.implementations;

import com.grupo5.proyecto.entities.Usuario;
import com.grupo5.proyecto.repositories.UsuarioRepository;
import com.grupo5.proyecto.services.EmailService;
import com.grupo5.proyecto.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserDetailsServiceImpl es la implementación de la interfaz UserDetailsService de Spring Security
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {



    // Inyección del repositorio de Usuario
    @Autowired
    UsuarioRepository usuarioRepository;

    // Inyección del servicio de Email
    @Autowired
    EmailService emailService;

    // Inyección del método de hash del password
    @Autowired
    PasswordEncoder argon2PasswordEncoder;

    @Override
    public Usuario encontrarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public boolean existeEmail(String email) {
        return (usuarioRepository.countByEmail(email) == 1) ? true : false;
    }

    @Override
    public boolean existeUsername(String username) { return (usuarioRepository.countByUsername(username) == 1) ? true : false;}

    @Override
    public Usuario encontrarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }


    @Override
    @Transactional
    public void guardar(Usuario usuario) {
        usuario.setPassword(argon2PasswordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
//        No activar hasta finalizar la fase de desarrollo o empezará a mandar correo a diestro y siniestro
//        emailService.enviarEmailSimple(new EmailDetalles(usuario.getEmail(),"Bienvenido al grupo 5",
//                """
//                ¡Hola!
//                Gracias por registrarte en la plataforma de juegos del grupo 5.
//                Saludos!!
//                """));

//         El mensaje es mejorable. Puede enviarsele también algún adjunto.
//                emailService.enviarEmailConAdjunto(new EmailDetalles(usuario.getEmail(),"Bienvenido al grupo 5",
//                """
//                ¡Hola!
//                Gracias por registrarte en la plataforma de juegos del grupo 5.
//                Saludos!!
//                ""","gitesquema.jpg"));
    }

    @Override
    public String intentosLogueo(String username) {
        return usuarioRepository.findIntentosLogueoByUsername(username);
    }

    public String mensajeForgotPassword (String email) {
        return "Hola de nuevo, ".concat(encontrarPorEmail(email).getUsername())
                .concat(":\n Al parecer has olvidado tu contraseña. Si es así, accede aquí.");
    }

    @Override
    public List<Usuario> listaAlumnos(Long id) {
        return null;
    }

}