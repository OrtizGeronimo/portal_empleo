package com.example.portal_empleo.repositories;

import com.example.portal_empleo.domain.Empresa;
import com.example.portal_empleo.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query(value = "SELECT * FROM usuario WHERE  usuario.username = :username",nativeQuery = true)
    Optional<Usuario> findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM usuario WHERE usuario.isActive = true",nativeQuery = true)
    List<Usuario> findAllByActive();
}
