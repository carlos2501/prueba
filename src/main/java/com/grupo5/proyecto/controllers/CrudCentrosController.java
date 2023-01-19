package com.grupo5.proyecto.controllers;


import com.grupo5.proyecto.entities.Centro;
import com.grupo5.proyecto.entities.Trivial;
import com.grupo5.proyecto.repositories.CentroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CrudCentrosController {

    @Autowired
    CentroRepository centroRepository;

    @GetMapping("/centros")
    public String home(Model model){
        model.addAttribute("centros", centroRepository.findAll());
        return "crudcentros/centroshome";
    }

    @PostMapping("/savecentros")
    public String save(Centro centro){
        centroRepository.save(centro);
        return "redirect:/centros";
    }

    @GetMapping("/editcentros/{id}")
    public String edit(@PathVariable Long id, Model model){
        Centro c = centroRepository.getById(id);
        model.addAttribute("centro", c);
        return "crudcentros/centrosedit";
    }

    @GetMapping("/deletecentros/{id}")
    public String delete(@PathVariable Long id, Model model){
        Centro c = centroRepository.getById(id);
        model.addAttribute("centro", c);
        centroRepository.delete(c);
        return "redirect:/centros";
    }
}
