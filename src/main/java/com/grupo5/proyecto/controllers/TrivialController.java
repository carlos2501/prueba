package com.grupo5.proyecto.controllers;

import com.grupo5.proyecto.entities.Trivial;
import com.grupo5.proyecto.repositories.TrivialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TrivialController {
    @Autowired
    TrivialRepository trivialRepository;

    @GetMapping("/trivial")
    public String admin(Model model, Authentication auth) {
        model.addAttribute("nombre", auth.getName());
        model.addAttribute("preguntas", trivialRepository.findAll());
        return "trivial/trivialhome";
    }

    @GetMapping("/create")
    public String create(){
        return "trivial/trivialcreate";
    }

    @PostMapping("/save")
    public String save(Trivial trivial){
        trivialRepository.save(trivial);
        return "redirect:/trivial";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Trivial t = trivialRepository.getById(id);
        model.addAttribute("trivial", t);
        return "trivial/trivialedit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
        Trivial t = trivialRepository.getById(id);
        model.addAttribute("trivial", t);
        trivialRepository.delete(t);
        return "redirect:/admin";
    }

}
