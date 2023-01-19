package com.grupo5.proyecto.services;

import com.grupo5.proyecto.dao.EmailDetalles;

/**
 * EmailService define las firmas de los métodos de servicio para la clase EmailDetalles
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */
public interface EmailService {
    boolean enviarEmailSimple(EmailDetalles email);
    boolean enviarEmailConAdjunto(EmailDetalles email);
    }
