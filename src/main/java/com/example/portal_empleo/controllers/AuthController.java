package com.example.portal_empleo.controllers;


import com.example.portal_empleo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("")
public class AuthController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/login")
    public String loginPrincipal(){return "login";}

    @GetMapping("/inicio")
    public String inicio(){
        return "Views/inicio";
    }

    @PostMapping("/busqueda")
    public String busqueda(Model model) {
        model.addAttribute("busqueda", "Hola mundo");
        return "Views/hola2";
    }





}
