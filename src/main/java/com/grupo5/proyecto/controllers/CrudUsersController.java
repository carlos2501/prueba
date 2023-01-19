package com.grupo5.proyecto.controllers;

import com.grupo5.proyecto.entities.Trivial;
import com.grupo5.proyecto.entities.UserSettings;
import com.grupo5.proyecto.entities.Usuario;
import com.grupo5.proyecto.repositories.UserSettingsRepository;
import com.grupo5.proyecto.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CrudUsersController {

    @Autowired
    UserSettingsRepository userSettingsRepository;

    @GetMapping("/user")
    public String admin(Model model, Authentication auth) {
        model.addAttribute("nombre", auth.getName());
        model.addAttribute("usuario", userSettingsRepository.findAll());
        return "cruduser/userhome";
    }

//    @GetMapping("/create")
//    public String create(){
//        return "crudUsers/userhome";
//    }

    @PostMapping("/saveuser")
    public String save(UserSettings usuario){
        userSettingsRepository.save(usuario);
        return "redirect:/user";
    }

    @GetMapping("/edituser/{id}")
    public String edit(@PathVariable Integer id, Model model){
        UserSettings u = userSettingsRepository.getById(id);
        model.addAttribute("usuario", u);
        return "cruduser/useredit";
    }

    @GetMapping("/deleteuser/{id}")
    public String delete(@PathVariable Integer id, Model model){
        UserSettings u = userSettingsRepository.getById(id);
        model.addAttribute("usuario", u);
        userSettingsRepository.delete(u);
        return "redirect:/user";
    }
}
