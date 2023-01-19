// Actualmente no está utilizándose en el proyecto //

package com.grupo5.proyecto.controllers;

import com.grupo5.proyecto.dao.EmailDetalles;
import com.grupo5.proyecto.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * EmailRestController contiene dos métodos mapeados para enviar un email con y sin fichero adjunto.
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */
@RestController
public class EmailRestController {
    @Autowired private EmailService emailService;

    /* Para enviar email deben enviarse a la ruta señalada los atributos del objeto email (por ejemplo con un JSON)
     *
     {
     "destinatario":"pepe@gmail.com",
     "asunto":"Mensaje",
     "mensaje":"Ey, te escribo de nuevo ",
     "adjunto":"gitesquema.jpg"
     }
     *
     * Si no se quiere incluir adjunto, no se pone el campo "adjunto". Los ficheros deben estar depositados en static/attachments
     */

    /**
     * sendMail envía un email normal desde /enviaremail
     * @param emailDetalles Objeto construido con los detalles destinatario, asunto y mensaje para enviar
     * @return
     */
    @PostMapping("/enviaremail")
    public String
    sendMail(@RequestBody EmailDetalles emailDetalles)
    {
        System.out.println(emailDetalles.getAdjunto());
        return Boolean.toString(emailService.enviarEmailSimple(emailDetalles));
    }

    /**
     * sendMailWithAttachment envía un email con adjunto desde /enviaremailadj
     * @param emailDetalles Objeto construido los detalles destinatario, asunto, mensaje y ruta del adjunto para enviar
     * @return
     */
    @PostMapping("/enviaremailadj")
    public String sendMailWithAttachment(
            @RequestBody EmailDetalles emailDetalles)
    {
        return Boolean.toString(emailService.enviarEmailConAdjunto(emailDetalles));
    }
}
