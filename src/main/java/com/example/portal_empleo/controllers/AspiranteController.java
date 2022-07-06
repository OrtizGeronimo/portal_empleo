package com.example.portal_empleo.controllers;

import com.example.portal_empleo.config.CurrentUser;
import com.example.portal_empleo.domain.Anuncio;
import com.example.portal_empleo.domain.Aspirante;
import com.example.portal_empleo.domain.Empresa;
import com.example.portal_empleo.domain.Usuario;
import com.example.portal_empleo.services.AnuncioService;
import com.example.portal_empleo.services.AspiranteService;
import com.example.portal_empleo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping(path = "/candidate")
public class AspiranteController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    AspiranteService aspiranteService;
    @Autowired
    AnuncioService anuncioService;

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
            return "Views/Candidate/aspirante";
        } catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/filter")
    public String filter(Model model, @RequestParam("date_publication") String fecha, @RequestParam("modalidad") String modalidad) {
        try{
            //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            //Date fecha = formatter.parse(date);
            //System.out.println(date);
            model.addAttribute("anuncios", anuncioService.findByDateAndMod(fecha, modalidad));
            return "Views/search";
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
            return "Views/Candidate/editar";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/edit")
    public String aspiranteEditar(Model model, @ModelAttribute Aspirante aspirante){
        try{
            aspiranteService.saveAspirante(aspirante);
            return "Views/Candidate/aspirante";
        }
        catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/apply/{id}")
    public String aspiranteAplicar(Model model, @PathVariable("id") Integer id){
        try{
            String username = CurrentUser.getCurrentUser();
            Usuario user = usuarioService.findByUsername(username);
            model.addAttribute("aspirante", aspiranteService.findByUser(user.getId()));
            model.addAttribute("anuncio", anuncioService.findById(id));
            return "Views/viewAnnouncement";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/apply/{id}")
    public String aspiranteApply(Model model, @ModelAttribute Anuncio anuncio, @PathVariable("id") Integer id){
        try{
            Aspirante aspirante = aspiranteService.findById(id);
            aspirante.getAnuncios().add(anuncio);
            model.addAttribute("anuncios", anuncioService.findAll());
            return "Views/Candidate/inicio";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}

