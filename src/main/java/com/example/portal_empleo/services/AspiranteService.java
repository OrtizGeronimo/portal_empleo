package com.example.portal_empleo.services;

import com.example.portal_empleo.domain.Aspirante;
import com.example.portal_empleo.domain.Empresa;
import com.example.portal_empleo.domain.Usuario;
import com.example.portal_empleo.repositories.AspiranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AspiranteService {

    @Autowired
    AspiranteRepository aspiranteRepository;

    public boolean save(Aspirante aspirante, String email, String psw){
        Usuario user = new Usuario();
        user.setUsername(email);
        user.setPassword(psw);
        user.setRol("CANDIDATE");
        aspirante.setUsuario(user);
        if (aspiranteRepository.save(aspirante) != null){
            return true;
        }
        return false;

    }

    public List<Aspirante> findAll(){
        return aspiranteRepository.findAll();
    }

    public Aspirante findById(Integer id){
        Optional<Aspirante> aspirante = aspiranteRepository.findById(id);
        return aspirante.get();
    }

    public Aspirante updateOne(Aspirante entity, Integer id){
        Optional<Aspirante> optional = aspiranteRepository.findById(id);
        Aspirante aspirante = optional.get();
        aspirante = aspiranteRepository.save(entity);
        return aspirante;
    }

    public Aspirante findByUser(Integer id){
        Optional<Aspirante> aspirante = aspiranteRepository.findByUser(id);
        return aspirante.get();
    }



}
