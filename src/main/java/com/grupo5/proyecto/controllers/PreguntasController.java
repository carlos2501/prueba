package com.grupo5.proyecto.controllers;

import com.grupo5.proyecto.entities.Question;
import com.grupo5.proyecto.services.implementations.PreguntasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@SessionScope
@Controller
public class PreguntasController {

	@Autowired
	PreguntasService preguntasService;

	List<Question> listaPreguntas;

	int numeroPreg = 1;
	int pregTotales = 5;
	int puntuacion = 0;
	int respuestaAnterior = 0;
	LocalDateTime finalizaPartida;

	@GetMapping("/preguntas")
	public String partida(Model model, HttpSession sesion) {
		finalizaPartida = LocalDateTime.now().plusMinutes(2);

		puntuacion = 0;
		sesion.setAttribute("tiempolimite",finalizaPartida);
		Question pregunta = preguntasService.generaPregunta();
		model.addAttribute("pregunta",pregunta);
		model.addAttribute("numeroPreg",numeroPreg);
		model.addAttribute("puntuacion",puntuacion);
		respuestaAnterior = pregunta.getAns();
		return "quiz";
	}

	@PostMapping("/preguntas")
	public String quiz(@RequestParam Integer numeroPreg, Model model, int respuesta, Question pregunta, HttpSession sesion) {
		System.out.println("Respuesta anterior" + respuestaAnterior);
		System.out.println("respuesta" + respuesta);
		if (respuestaAnterior == respuesta) {
			puntuacion++;
			System.out.println("Puntuacion en el if" + puntuacion);
		}
		while (numeroPreg < pregTotales && LocalDateTime.now().isBefore(((LocalDateTime) sesion.getAttribute("tiempolimite")))) {
			numeroPreg++;
			System.out.println("Número pregunta" + numeroPreg);
			pregunta = preguntasService.generaPregunta();
			model.addAttribute("numeroPreg",numeroPreg);
			model.addAttribute("pregunta",pregunta);
			respuestaAnterior = pregunta.getAns();
			return "quiz";
		}
		model.addAttribute("puntuacion",puntuacion);
		return "result";
	}
	
//	@PostMapping("/submit")
//	public String submit(@ModelAttribute QuestionForm qForm, Model m) {
//		if(!submitted) {
//			result.setTotalCorrect(qService.getResult(qForm));
//			qService.saveScore(result);
//			submitted = true;
//		}
//
//		return "result.html";
//	}
//
//	@GetMapping("/score")
//	public String score(Model m) {
//		List<Result> sList = qService.getTopScore();
//		m.addAttribute("sList", sList);
//
//		return "scoreboard.html";
//	}

}
