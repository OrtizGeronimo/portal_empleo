package com.example.portal_empleo.controllers;


import com.example.portal_empleo.config.CurrentUser;
import com.example.portal_empleo.domain.Aspirante;
import com.example.portal_empleo.domain.Empresa;
import com.example.portal_empleo.domain.Usuario;
import com.example.portal_empleo.services.AnuncioService;
import com.example.portal_empleo.services.AspiranteService;
import com.example.portal_empleo.services.EmpresaService;
import com.example.portal_empleo.services.UsuarioService;
//import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;


@Controller
@RequestMapping("")
public class AuthController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    AspiranteService aspiranteService;
    @Autowired
    EmpresaService empresaService;
    @Autowired
    AnuncioService anuncioService;
    @Autowired
    EmpresaService empresaService;

    @GetMapping("/login")
    public String loginPrincipal(Model model){
        model.addAttribute("aspirante", new Aspirante());
        model.addAttribute("empresa", new Empresa());
        return "login";
    }

    @GetMapping("/inicio")
    public String inicio(Model model){
<<<<<<< Updated upstream
        String username = CurrentUser.getCurrentUser();
        Usuario user = usuarioService.findByUsername(username);
        String userRole = user.getRol();
        model.addAttribute("usuario",user);
        if (userRole.equalsIgnoreCase("ADMIN")){
            model.addAttribute("usuarios", usuarioService.findAll());
            return "Views/Admin/inicio";
        } else if (userRole.equalsIgnoreCase("CANDIDATE")){
            model.addAttribute("anuncios", anuncioService.findAll());
            return "Views/Candidate/inicio";
        } else if (userRole.equalsIgnoreCase("COMPANY")) {
            int userId = user.getId();
            Empresa emp = empresaService.findByUser(userId);
            model.addAttribute("anuncios", anuncioService.findByCompanyId(emp.getId()));
            model.addAttribute("empresa", emp);
            return "Views/Company/inicio";
        }
        return "/error";
=======
        model.addAttribute("anuncios", anuncioService.findAll());
        String username = CurrentUser.getCurrentUser();
        Usuario user = usuarioService.findByUsername(username);
        Empresa empresa = empresaService.findByUser(user.getId());
        if(empresa!=null){
            model.addAttribute("empresa", empresa);
            return "Views/inicio";
        }
        model.addAttribute("aspirante", aspiranteService.findByUser(user.getId()));
        return "Views/inicio";
>>>>>>> Stashed changes
    }



    @GetMapping("/search")
    public String busqueda(Model model, @RequestParam(value = "query", required = false) String word) {
        model.addAttribute("anuncios", anuncioService.findByWord(word));
        return "Views/search";
    }
}
