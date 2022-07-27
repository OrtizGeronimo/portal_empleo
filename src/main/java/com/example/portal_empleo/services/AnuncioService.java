package com.example.portal_empleo.services;

import com.example.portal_empleo.domain.Anuncio;
import com.example.portal_empleo.domain.Empresa;
import com.example.portal_empleo.repositories.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnuncioService {

    @Autowired
    AnuncioRepository anuncioRepository;

    public Page<Anuncio> findAll(Pageable pageable){
        Page<Anuncio> anuncios = anuncioRepository.findAll(pageable);
        return anuncios;
    }

    public Page<Anuncio> findByWord(String word, Pageable pageable){
        Page<Anuncio> anuncios = anuncioRepository.findByWord(word, pageable);
        return anuncios;
    }

    public Anuncio findById(Integer id){
        Optional<Anuncio> anuncio = anuncioRepository.findById(id);
        return anuncio.get();
    }

    public Page<Anuncio> findByCompanyId(Integer id, Pageable pageable){
        Page<Anuncio> anuncios = anuncioRepository.findByCompanyId(id, pageable);
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
