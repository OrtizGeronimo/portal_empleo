package com.example.portal_empleo.controllers;

import com.example.portal_empleo.config.CurrentUser;
import com.example.portal_empleo.domain.Aspirante;
import com.example.portal_empleo.domain.Empresa;
import com.example.portal_empleo.domain.Usuario;
import com.example.portal_empleo.services.AspiranteService;
import com.example.portal_empleo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public String aspiranteProfile(Model model){
        try{
            String username = CurrentUser.getCurrentUser();
            Usuario user = usuarioService.findByUsername(username);
            model.addAttribute("aspirante", aspiranteService.findByUser(user.getId()));
            return "Views/Aspirantes/aspirante";
        } catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/edit")
    public String aspiranteView(Model model){
        try{
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDetails userDetails = null;
            if (principal instanceof UserDetails){
                userDetails = (UserDetails) principal;
            }
            String username = userDetails.getUsername();
            Usuario user = usuarioService.findByUsername(username);
            model.addAttribute("aspirante", aspiranteService.findByUser(user.getId()));
            return "Views/Aspirantes/editar";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/edit")
    public String aspiranteEditar(Model model, @ModelAttribute Aspirante aspirante, @PathVariable("id") Integer id){
        try{
            aspiranteService.updateOne(aspirante,id);
            return "Views/Aspirantes/aspirante";
        }
        catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}

