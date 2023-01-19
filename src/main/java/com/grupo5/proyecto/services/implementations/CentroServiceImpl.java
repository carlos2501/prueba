package com.grupo5.proyecto.services.implementations;

import com.grupo5.proyecto.entities.Centro;
import com.grupo5.proyecto.repositories.CentroRepository;
import com.grupo5.proyecto.services.CentroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * CentroServiceImpl es la implementación de la interfaz CentroService
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */
@Service
public class CentroServiceImpl implements CentroService {

    @Autowired
    CentroRepository centroRepository;

    @Override
    public List<String> listarProvincias() {
        return centroRepository.listProvincias();
    }

    @Override
    public List<String> buscarPorProvincia(String provincia) {
        return centroRepository.findByProvincia(provincia);
    }

    @Override
    public List<Centro> buscarPorMunicipio(String municipio) {return centroRepository.findByMunicipio(municipio);}

    @Override
    public List<Centro> listarTiposDeCentroEnMunicipio(List<Centro> lista) {
        return lista.stream().filter(distinctByKey(k->k.getTipo())).sorted(Comparator.comparing(Centro::getTipo)).toList();
    }

    @Override
    public List<Centro> listarCentrosPorTipo(String municipio, String tipo) {
        return centroRepository.findByMunicipioAndTipo(municipio, tipo).stream().sorted(Comparator.comparing(Centro::getNombre)).toList();
    }

    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
