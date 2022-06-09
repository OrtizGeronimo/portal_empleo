package com.example.portal_empleo.repositories;

import com.example.portal_empleo.domain.Aspirante;
import com.example.portal_empleo.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AspiranteRepository extends JpaRepository<Aspirante, Integer> {
    @Query(value = "SELECT * FROM aspirante WHERE aspirante.fk_usuario = :id",nativeQuery = true)
    Optional<Aspirante> findByUser(@Param("id") Integer id);
}
