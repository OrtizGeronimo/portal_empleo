package com.example.portal_empleo.services;

import com.example.portal_empleo.domain.Anuncio;
import com.example.portal_empleo.repositories.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public Anuncio findById(Integer id){
        Optional<Anuncio> anuncio = anuncioRepository.findById(id);
        return anuncio.get();
    }

    public List<Anuncio> findByCompanyId(Integer id){
        List<Anuncio> anuncios = anuncioRepository.findByCompanyId(id);
        return anuncios;
    }

    public List<Anuncio> findByDateAndMod(String fecha, String modalidad){
        List<Anuncio> anuncios = anuncioRepository.findByDateAndMod(fecha, modalidad);
        return anuncios;
    }
}
