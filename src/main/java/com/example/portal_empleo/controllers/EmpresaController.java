package com.example.portal_empleo.controllers;



import com.example.portal_empleo.config.CurrentUser;
import com.example.portal_empleo.domain.Anuncio;
import com.example.portal_empleo.domain.Aspirante;
import com.example.portal_empleo.domain.Empresa;
import com.example.portal_empleo.domain.Usuario;
import com.example.portal_empleo.services.AnuncioService;
import com.example.portal_empleo.services.EmpresaService;
import com.example.portal_empleo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    AnuncioService anuncioService;

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
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails userDetails = null;
//        if (principal instanceof UserDetails){
//            userDetails = (UserDetails) principal;
//        }
        String username = CurrentUser.getCurrentUser();
        Usuario user = usuarioService.findByUsername(username);
        model.addAttribute("empresa", empresaService.findByUser(user.getId()));
        model.addAttribute("anuncio", new Anuncio());
        return "Views/Formularios/anuncio";
    }

    @PostMapping("announcement/create/{id}")
    public String createAnnouncement(@ModelAttribute Anuncio anuncio, @PathVariable("id") Integer id){
        anuncioService.saveAnnouncement(anuncio, id);
        return "Views/aspirante";
    }

    @GetMapping("announcement/view")
    public String viewAnnouncements(Model model, @PathVariable("id") Integer id){
        model.addAttribute("anuncios", anuncioService.findByCompanyId(id));
        return "Views/announcements";
    }


}
