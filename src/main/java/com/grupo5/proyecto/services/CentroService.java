package com.grupo5.proyecto.services;

import com.grupo5.proyecto.entities.Centro;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CentroService define las firmas de los métodos de servicio para la clase Centro
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */

public interface CentroService {
    List<String> listarProvincias();
    List<String> buscarPorProvincia(String provincia);
    List<Centro> buscarPorMunicipio(String municipio);
    List<Centro> listarTiposDeCentroEnMunicipio(List<Centro> lista);
    List<Centro> listarCentrosPorTipo(String centro, String tipo);
}
