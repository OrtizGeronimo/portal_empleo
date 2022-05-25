package com.example.portal_empleo.repositories;

import com.example.portal_empleo.domain.Aspirante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AspiranteRepository extends JpaRepository<Aspirante, Integer> {

}
