package com.example.portal_empleo.controllers;



import com.example.portal_empleo.domain.Empresa;
import com.example.portal_empleo.services.EmpresaService;
import com.example.portal_empleo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class EmpresaController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    EmpresaService empresaService;

    @PostMapping("/register")
    public String register(@ModelAttribute Empresa empresa, @RequestParam("email") String email, @RequestParam("psw") String psw) {
        if (usuarioService.findByUsername(email) != null) {
            return "/error";
        }
        empresaService.save(empresa, email, psw);
        return "/Views/inicio";
    }

}
