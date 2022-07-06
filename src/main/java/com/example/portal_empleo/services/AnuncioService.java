package com.example.portal_empleo.services;

import com.example.portal_empleo.domain.Anuncio;
import com.example.portal_empleo.domain.Empresa;
import com.example.portal_empleo.repositories.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnuncioService {

    @Autowired
    AnuncioRepository anuncioRepository;

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

    public List<Anuncio> filterCompanyAnnouncements(Integer id, String fecha, String modalidad){
        List<Anuncio> anuncios = anuncioRepository.filterCompanyAnnouncements(id,fecha,modalidad);
        return anuncios;
    }

    public boolean saveAnnouncement(Anuncio anuncio, Empresa empresa){
        anuncio.setEstadoAnuncio("activo");
        Date date = new Date();
        anuncio.setFechaPublicacion(date);
        anuncio.setEmpresa(empresa);
        anuncioRepository.save(anuncio);
        return true;
    }

    public boolean deleteAnnouncement(Integer id){
        anuncioRepository.deleteById(id);
        return true;
    }
}
