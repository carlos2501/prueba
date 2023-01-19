package com.grupo5.proyecto.services.implementations;

import com.grupo5.proyecto.dao.EmailDetalles;
import com.grupo5.proyecto.services.EmailService;
import com.grupo5.proyecto.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * EmailServiceImpl es la implementación de la interfaz EmailService
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */


@Service
public class EmailServiceImpl implements EmailService {
    // Se inyecta el API para enviar emails de Java
    @Autowired
    private JavaMailSender javaMailSender;

    // Nombre del emisor del email
    @Value("${spring.mail.username}") private String emisor;

    @Override
    public boolean enviarEmailSimple(EmailDetalles email) {
        try {
            // Se crea un objeto que representa al email simple
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Se indican los detalles de envío: Emisor, destinatario, asunto y mensaje
            mailMessage.setFrom(emisor);
            mailMessage.setTo(email.getDestinatario());
            mailMessage.setSubject(email.getAsunto());
            mailMessage.setText(email.getMensaje());

            // Se envía el email
            javaMailSender.send(mailMessage);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean enviarEmailConAdjunto(EmailDetalles email) {
        //  Se crea un objeto que representa al email mime
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            // Para indicar que este mensaje con adjunto lleva varias partes.
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(emisor);
            mimeMessageHelper.setTo(email.getDestinatario());
            mimeMessageHelper.setText(email.getMensaje());
            mimeMessageHelper.setSubject(email.getAsunto());

            // Incluimos el adjunto para enviarlo
            String ruta = "static\\attachments\\" + email.getAdjunto();
            String archivo = email.getAdjunto();
            System.out.println(archivo);
            mimeMessageHelper.addAttachment(archivo,new ClassPathResource(ruta));
            javaMailSender.send(mimeMessage);

            // Enviamos el email
            javaMailSender.send(mimeMessage);
            return true;
        }
        catch (MessagingException e) {
            return false;
        }
    }
}
