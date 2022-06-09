package com.example.portal_empleo.repositories;

import com.example.portal_empleo.domain.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

    @Query(value = "SELECT * FROM anuncio WHERE anuncio.fk_empresa = :id", nativeQuery = true)
    List<Anuncio> findByCompanyId(@Param("id") Integer id);

    @Query(value = "SELECT * FROM anuncio WHERE anuncio.titulo LIKE %:word%", nativeQuery = true)
    List<Anuncio> findByWord(@Param("word") String word);
}
