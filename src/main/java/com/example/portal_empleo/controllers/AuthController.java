package com.example.portal_empleo.controllers;


import com.example.portal_empleo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("")
public class AuthController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/login")
    public String loginPrincipal(){return "login";}

    @GetMapping("/hola")
    public String hola(){
        return "hola";
    }
    @GetMapping("/hola2")
    public String hola2(){
        return "hola2";
    }




}
