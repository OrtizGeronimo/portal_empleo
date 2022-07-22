package com.example.portal_empleo.controllers;

import com.example.portal_empleo.domain.Usuario;
import com.example.portal_empleo.services.AnuncioService;
import com.example.portal_empleo.services.AspiranteService;
import com.example.portal_empleo.services.EmpresaService;
import com.example.portal_empleo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    AspiranteService aspiranteService;
    @Autowired
    EmpresaService empresaService;
    @Autowired
    AnuncioService anuncioService;

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id){
        try{
            Usuario user = usuarioService.findById(id);
            if (user.getRol().equalsIgnoreCase("COMPANY")){
                model.addAttribute("empresa", empresaService.findByUser(id));
                return "Views/Admin/viewCompany";
            }
            model.addAttribute("aspirante", aspiranteService.findByUser(id));
            return "Views/Admin/viewCandidate";
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
