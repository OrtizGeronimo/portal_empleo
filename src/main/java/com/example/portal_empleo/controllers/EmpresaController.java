package com.example.portal_empleo.controllers;



import com.example.portal_empleo.domain.Anuncio;
import com.example.portal_empleo.domain.Aspirante;
import com.example.portal_empleo.domain.Empresa;
import com.example.portal_empleo.services.EmpresaService;
import com.example.portal_empleo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/company")
public class EmpresaController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    EmpresaService empresaService;

    @PostMapping("/register")
    public String register(@ModelAttribute Empresa empresa, @RequestParam("email") String email, @RequestParam("psw") String psw, Model model) {
        if (usuarioService.findByUsername(email) != null) {
            return "/error";
        }
        model.addAttribute("aspirante", new Aspirante());
        model.addAttribute("empresa", new Empresa());
        empresaService.save(empresa, email, psw);
        return "login";
    }

    @GetMapping("/account")
    public String companyProfile(){
        return "Views/empresa";
    }

    @GetMapping("/edit")
    public String empresaView(Model model){
        model.addAttribute("empresa", empresaService.findById(6));
        return "Views/editar";
    }

    @PostMapping("/edit")
    public String empresaEditar(@ModelAttribute("empresa") Empresa empresa){
        empresaService.updateOne(empresa,6);
        return "Views/editar";
    }

    @GetMapping("/announcement/create")
    public String getViewAnnouncement(Model model){
        model.addAttribute("anuncio", new Anuncio());
        return "Views/anuncio";
    }

    @PostMapping("announcement/create")
    public String createAnnouncement(@ModelAttribute Anuncio anuncio){
        empresaService.updateAnnouncement(anuncio, 1);
        return "Views/aspirante";
    }


}
