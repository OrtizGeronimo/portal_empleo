package com.example.portal_empleo.controllers;

import com.example.portal_empleo.domain.Aspirante;
import com.example.portal_empleo.services.AspiranteService;
import com.example.portal_empleo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/candidate")
public class AspiranteController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    AspiranteService aspiranteService;

    @PostMapping("/register")
    public String register(@ModelAttribute Aspirante aspirante, @RequestParam("email") String email, @RequestParam("psw") String psw) {
        if (usuarioService.findByUsername(email) != null) {
            return "/error";
        }
        aspiranteService.save(aspirante, email, psw);
        return "Views/inicio";
    }
}

