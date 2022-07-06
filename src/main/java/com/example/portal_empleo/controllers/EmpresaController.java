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

    @PostMapping("/filter")
    public String companyFilter(Model model, @RequestParam("date_publication") String fecha, @RequestParam("modalidad") String modalidad){
        try{
            String username = CurrentUser.getCurrentUser();
            Usuario user = usuarioService.findByUsername(username);
            Empresa empresa = empresaService.findByUser(user.getId());
            model.addAttribute("anuncios", anuncioService.filterCompanyAnnouncements(empresa.getId(), fecha, modalidad));
            return "Views/search";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/account")
    public String companyProfile(Model model){
        try{
            String username = CurrentUser.getCurrentUser();
            Usuario user = usuarioService.findByUsername(username);
            model.addAttribute("empresa", empresaService.findByUser(user.getId()));
            return "Views/Company/empresa";
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/edit")
    public String empresaViewEdit(Model model){
        try{
            String username = CurrentUser.getCurrentUser();
            Usuario user = usuarioService.findByUsername(username);
            model.addAttribute("empresa", empresaService.findByUser(user.getId()));
            return "Views/Company/editar";
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/edit")
    public String empresaEditar(Model model, @ModelAttribute("empresa") Empresa empresa, @PathVariable("id") Integer id){
        try{
            empresaService.updateOne(empresa, id);
            return "Views/Company/empresa";
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/announcement/create")
    public String getViewAnnouncement(Model model){
        try{
            String username = CurrentUser.getCurrentUser();
            Usuario user = usuarioService.findByUsername(username);
            model.addAttribute("empresa", empresaService.findByUser(user.getId()));
            model.addAttribute("anuncio", new Anuncio());
            return "Views/Formularios/anuncio";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("announcement/create")
    public String createAnnouncement(Model model, @ModelAttribute Anuncio anuncio){
        try{
            String username = CurrentUser.getCurrentUser();
            Usuario user = usuarioService.findByUsername(username);
            Empresa empresa = empresaService.findByUser(user.getId());
            anuncioService.saveAnnouncement(anuncio, empresa);
            model.addAttribute("empresa", empresa);
            model.addAttribute("anuncios", anuncioService.findByCompanyId(empresa.getId()));
            return "Views/Company/inicio";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("announcement/edit/{id}")
    public String viewEditAnnouncement(Model model, @PathVariable("id") Integer id){
        try{
            model.addAttribute("anuncio", anuncioService.findById(id));
            return "Views/Company/announcementEdit";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("announcement/edit")
    public String EditAnnouncement(Model model, @ModelAttribute Anuncio anuncio){
        try{
            String username = CurrentUser.getCurrentUser();
            Usuario user = usuarioService.findByUsername(username);
            Empresa empresa = empresaService.findByUser(user.getId());
            anuncioService.saveAnnouncement(anuncio,empresa);
            model.addAttribute("empresa", empresa);
            model.addAttribute("anuncios", anuncioService.findByCompanyId(empresa.getId()));
            return "Views/Company/inicio";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("announcement/delete/{id}")
    public String viewDeleteAnnouncement(Model model, @PathVariable("id") Integer id){
        try{
            model.addAttribute("anuncio", anuncioService.findById(id));
            return "Views/Company/announcementDelete";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("announcement/delete/{id}")
    public String DeleteAnnouncement(Model model, @PathVariable("id") Integer id){
        try{
            anuncioService.deleteAnnouncement(id);
            String username = CurrentUser.getCurrentUser();
            Usuario user = usuarioService.findByUsername(username);
            Empresa empresa = empresaService.findByUser(user.getId());
            model.addAttribute("empresa", empresa);
            model.addAttribute("anuncios", anuncioService.findByCompanyId(empresa.getId()));
            return "Views/Company/inicio";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("announcement/view/{id}")
    public String viewAnnouncements(Model model, @PathVariable("id") Integer id){
        try{
            model.addAttribute("anuncios", anuncioService.findByCompanyId(id));
            return "Views/Empresas/announcements";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
