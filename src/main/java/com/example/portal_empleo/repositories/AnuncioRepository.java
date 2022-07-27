package com.example.portal_empleo.repositories;

import com.example.portal_empleo.domain.Anuncio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {

    @Query(
            value = "SELECT * FROM anuncio WHERE anuncio.fk_empresa = :id",
            countQuery = "SELECT count(*) FROM anuncio",
            nativeQuery = true)
    Page<Anuncio> findByCompanyId(@Param("id") Integer id, Pageable pageable);

    @Query(
            value = "SELECT * FROM anuncio WHERE anuncio.titulo LIKE %:word%",
            countQuery = "SELECT count(*) FROM anuncio",
            nativeQuery = true)
    Page<Anuncio> findByWord(@Param("word") String word, Pageable pageable);

    @Query(value = "SELECT * FROM anuncio WHERE anuncio.fecha_publicacion LIKE :fecha% AND anuncio.modalidad = :word", nativeQuery = true)
    List<Anuncio> findByDateAndMod(@Param("fecha") String fecha, @Param("word") String word);

    @Query(value = "SELECT * FROM anuncio WHERE anuncio.fecha_publicacion LIKE :fecha% AND anuncio.modalidad = :word AND anuncio.fk_empresa = :id", nativeQuery = true)
    List<Anuncio> filterCompanyAnnouncements(@Param("id") Integer id, @Param("fecha") String fecha, @Param("word") String word);
}
