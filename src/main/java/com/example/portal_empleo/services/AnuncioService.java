package com.example.portal_empleo.services;

import com.example.portal_empleo.domain.Anuncio;
import com.example.portal_empleo.repositories.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnuncioService {

    @Autowired
    AnuncioRepository anuncioRepository;
    @Autowired
    EmpresaService empresaService;

    public boolean saveAnnouncement(Anuncio anuncio, Integer id){
        anuncio.setEstadoAnuncio("activo");
        anuncio.setEmpresa(empresaService.findById(id));
        anuncioRepository.save(anuncio);
        return true;
    }

    public List<Anuncio> findAll(){
        List<Anuncio> anuncios = anuncioRepository.findAll();
        return anuncios;
    }

    public List<Anuncio> findByWord(String word){
        List<Anuncio> anuncios = anuncioRepository.findByWord(word);
        return anuncios;
    }

    public List<Anuncio> findByCompanyId(Integer id){
        List<Anuncio> anuncios = anuncioRepository.findByCompanyId(id);
        return anuncios;
    }
}
