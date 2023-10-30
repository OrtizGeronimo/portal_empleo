package com.example.portal_empleo.controllers;

import com.example.portal_empleo.domain.Anuncio;
import com.example.portal_empleo.domain.Usuario;
import com.example.portal_empleo.services.AnuncioService;
import com.example.portal_empleo.services.AspiranteService;
import com.example.portal_empleo.services.EmpresaService;
import com.example.portal_empleo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @GetMapping("/announcements")
    public String getAnnouncements(Model model, @RequestParam Map<String, Object> params){
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1 ) : 0; //Obtenemos la pagina solicitada por usuario, primera vez es 0
        PageRequest pageRequest = PageRequest.of(page, 5);
        int totalPage;
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
        model.addAttribute("anuncios", anuncioService.findAll(pageRequest));
        return "Views/Admin/anuncios";
    }

}
