package com.example.portal_empleo.controllers;


import com.example.portal_empleo.domain.Aspirante;
import com.example.portal_empleo.domain.Empresa;
import com.example.portal_empleo.domain.Usuario;
import com.example.portal_empleo.services.AspiranteService;
import com.example.portal_empleo.services.UsuarioService;
//import com.sun.org.apache.xpath.internal.operations.Mod;
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
    @Autowired
    AspiranteService aspiranteService;

    @GetMapping("/login")
    public String loginPrincipal(Model model){
        model.addAttribute("aspirante", new Aspirante());
        model.addAttribute("empresa", new Empresa());
        return "login";
    }

    @GetMapping("/inicio")
    public String inicio(Model model){
        model.addAttribute("aspirantes", aspiranteService.findAll());
        return "Views/inicio";
    }

    @GetMapping("/busqueda")
    public String busqueda(Model model, @RequestParam String word) {

        model.addAttribute("busqueda", word);
        return "Views/hola2";
    }

    @GetMapping("aspirante/cuenta")
    public String aspiranteProfile(Model model){

        return "Views/aspirante";
    }

    @GetMapping("empresa/cuenta")
    public String companyProfile(){
        return "Views/empresa";
    }

    @GetMapping("aspirante/editar")
    public String aspiranteEditar(Model model){

        return "Views/editar";
    }
}
