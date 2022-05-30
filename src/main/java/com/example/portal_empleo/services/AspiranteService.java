package com.example.portal_empleo.services;

import com.example.portal_empleo.domain.Aspirante;
import com.example.portal_empleo.domain.Usuario;
import com.example.portal_empleo.repositories.AspiranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AspiranteService {

    @Autowired
    AspiranteRepository aspiranteRepository;

    public boolean save(Aspirante aspirante, String email, String psw){
        Usuario user = new Usuario();
        user.setUsername(email);
        user.setPassword(psw);
        aspirante.setUsuario(user);
        if (aspiranteRepository.save(aspirante) != null){
            return true;
        }
        return false;

    }



}
