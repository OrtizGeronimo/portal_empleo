package com.example.portal_empleo.services;

import com.example.portal_empleo.domain.Aspirante;
import com.example.portal_empleo.repositories.AspiranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AspiranteService {

    @Autowired
    AspiranteRepository aspiranteRepository;

    public boolean save(Aspirante aspirante){
        if (aspiranteRepository.save(aspirante) != null){
            return true;
        }
        return false;

    }



}
