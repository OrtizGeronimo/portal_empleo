package com.example.portal_empleo.controllers;


import com.example.portal_empleo.config.CurrentUser;
import com.example.portal_empleo.domain.Anuncio;
import com.example.portal_empleo.domain.Aspirante;
import com.example.portal_empleo.domain.Empresa;
import com.example.portal_empleo.domain.Usuario;
import com.example.portal_empleo.services.AnuncioService;
import com.example.portal_empleo.services.AspiranteService;
import com.example.portal_empleo.services.EmpresaService;
import com.example.portal_empleo.services.UsuarioService;
//import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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

    @GetMapping("/login")
    public String loginPrincipal(Model model){
        model.addAttribute("aspirante", new Aspirante());
        model.addAttribute("empresa", new Empresa());
        return "login";
    }

    @GetMapping("/")
    public String inicio(Model model, @RequestParam Map<String, Object> params){
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1 ) : 0; //Obtenemos la pagina solicitada por usuario, primera vez es 0
        PageRequest pageRequest = PageRequest.of(page, 5); //Definimos la request, contiene la pagina solicitada y el tamaño de la sublista
        int totalPage;
        String username = CurrentUser.getCurrentUser();
        Usuario user = usuarioService.findByUsername(username);
        String userRole = user.getRol();
        model.addAttribute("usuario",user);
        if (userRole.equalsIgnoreCase("ADMIN")){
            model.addAttribute("usuarios", usuarioService.findAll());
            return "Views/Admin/inicio";
        } else if (userRole.equalsIgnoreCase("CANDIDATE")){
            Page<Anuncio> pageAnuncio = anuncioService.findAll(pageRequest); //Buscamos con la request solicitada
            totalPage = pageAnuncio.getTotalPages(); //Corroboramos el total de paginas teniendo en cuenta las condiciones
            if(totalPage>0){
                List<Integer> pages = IntStream.rangeClosed(1,totalPage).boxed().collect(Collectors.toList()); //Obtenemos total de paginas
                model.addAttribute("pages", pages);
            }
            model.addAttribute("anuncios", pageAnuncio.getContent());
            model.addAttribute("current", page+1);
            model.addAttribute("next", page+2);
            model.addAttribute("prev", page);
            model.addAttribute("last", totalPage);
            return "Views/Candidate/inicio";
            } else if (userRole.equalsIgnoreCase("COMPANY")) {
                int userId = user.getId();
                Empresa emp = empresaService.findByUser(userId);
                Page<Anuncio> pageAnuncio = anuncioService.findByCompanyId(emp.getId(), pageRequest);
                totalPage = pageAnuncio.getTotalPages();
                if(totalPage>0){
                    List<Integer> pages = IntStream.rangeClosed(1,totalPage).boxed().collect(Collectors.toList()); //Obtenemos total de paginas
                    model.addAttribute("pages", pages);
                }
                model.addAttribute("anuncios", pageAnuncio.getContent());
                model.addAttribute("empresa", emp);
                model.addAttribute("current", page+1);
                model.addAttribute("next", page+2);
                model.addAttribute("prev", page);
                model.addAttribute("last", totalPage);
                return "Views/Company/inicio";
            }
        return "error";
    }

    @GetMapping("/search")
    public String busqueda(Model model, @RequestParam(value = "query", required = false) String word) {
        try{
            String username = CurrentUser.getCurrentUser();
            Usuario user = usuarioService.findByUsername(username);
            String userRole = user.getRol();
            if (userRole.equalsIgnoreCase("ADMIN")){
                model.addAttribute("anuncios", anuncioService.findByWord(word));
                return "Views/Admin/search";
            } else if(userRole.equalsIgnoreCase("COMPANY")){
                model.addAttribute("anuncios", anuncioService.findByWord(word));
                return "Views/Company/search";
            }else{
                model.addAttribute("anuncios", anuncioService.findByWord(word));
                return "Views/Candidate/search";
            }
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
