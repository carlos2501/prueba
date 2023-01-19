package com.grupo5.proyecto.controllers;

import com.grupo5.proyecto.entities.Partida;
import com.grupo5.proyecto.services.IfxPartidaSrvc;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {
    private IfxPartidaSrvc partidasrvc;

    public PartidaController(IfxPartidaSrvc partidasrvc){ this.partidasrvc=partidasrvc;}

    @GetMapping("/lista")
    public ResponseEntity<List<Partida>>getPartidas() {
        return ResponseEntity.ok().body(partidasrvc.finAll());
    }

    @PostMapping("/nueva")
    public ResponseEntity<Partida>nuevaPartida(@RequestBody Partida partida) {
        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/partidas/nueva").toUriString());
        return ResponseEntity.created(uri).body(partidasrvc.nuevaPartida(partida));
    }


    @GetMapping("empezar/{idpartida}") //<-- Solo accesible para el profe que es el que inicia la partida
    public String iniciarParida() {
        // 1 - Se guardan en la sesión los datos de la partida: id, tiempo, num. preguntas, ...

        // 2 - Devolver pantalla al profe con los datos guardado y un mensaje indicando que diga a los alumnos que accedan
        return "partida_iniciada";
    }
}
