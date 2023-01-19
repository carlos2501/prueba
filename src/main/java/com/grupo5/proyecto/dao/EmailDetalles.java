package com.grupo5.proyecto.dao;

/**
 * EmailDetalles es la clase que representa los detalles del email que se envian desde el servicio EmailService
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */
public class EmailDetalles {
    private String destinatario;
    private String asunto;
    private String mensaje;
    private String adjunto;

    public EmailDetalles() {
    }

    // Constructor para mensajes simples
    public EmailDetalles(String destinatario, String asunto, String mensaje) {
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    // Constructor para mensajes con adjunto
    public EmailDetalles(String destinatario, String asunto, String mensaje, String adjunto) {
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.adjunto = adjunto;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(String adjunto) {
        this.adjunto = adjunto;
    }
}
