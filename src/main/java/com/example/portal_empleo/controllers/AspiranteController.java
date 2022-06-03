package com.example.portal_empleo.controllers;

import com.example.portal_empleo.domain.Aspirante;
import com.example.portal_empleo.domain.Empresa;
import com.example.portal_empleo.services.AspiranteService;
import com.example.portal_empleo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/candidate")
public class AspiranteController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    AspiranteService aspiranteService;

    @PostMapping("/register")
    public String register(@ModelAttribute Aspirante aspirante, @RequestParam("email") String email, @RequestParam("psw") String psw, Model model) {
        if (usuarioService.findByUsername(email) != null) {
            return "error";
        }
        model.addAttribute("aspirante", new Aspirante());
        model.addAttribute("empresa", new Empresa());
        aspiranteService.save(aspirante, email, psw);
        return "login";
    }

    @GetMapping("/account")
    public String aspiranteProfile(){
        return "Views/aspirante";
    }

    @GetMapping("/edit")
    public String aspiranteView(Model model){
        model.addAttribute("aspirante", aspiranteService.findById(6));
        return "Views/editar";
    }

    @PostMapping("/edit")
    public String aspiranteEditar(@ModelAttribute("aspirante") Aspirante aspirante){
        aspiranteService.updateOne(aspirante,6);
        return "Views/editar";
    }
}

