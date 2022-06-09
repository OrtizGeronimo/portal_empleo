package com.example.portal_empleo.services;



import com.example.portal_empleo.domain.Anuncio;
import com.example.portal_empleo.domain.Aspirante;
import com.example.portal_empleo.domain.Empresa;
import com.example.portal_empleo.domain.Usuario;
import com.example.portal_empleo.repositories.AnuncioRepository;
import com.example.portal_empleo.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    public boolean save(Empresa empresa, String email, String psw){
        Usuario user = new Usuario();
        user.setUsername(email);
        user.setPassword(psw);
        user.setRol("COMPANY");
        empresa.setUsuario(user);
        if (empresaRepository.save(empresa) != null){
            return true;
        }
        return false;
    }

    public Empresa findById(Integer id){
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.get();
    }

    public Empresa findByUser(Integer id){
        Optional<Empresa> empresa = empresaRepository.findByUser(id);
        return empresa.get();
    }

    public Empresa updateOne(Empresa entity, Integer id){
        Optional<Empresa> optional = empresaRepository.findById(id);
        Empresa empresa = optional.get();
        empresa = empresaRepository.save(entity);
        return empresa;
    }

}
