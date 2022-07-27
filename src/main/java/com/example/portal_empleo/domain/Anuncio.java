package com.example.portal_empleo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Table(name = "anuncio")
@NoArgsConstructor
@AllArgsConstructor

public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;
    private String descripcion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaPublicacion;

    private String modalidad;
    private String estadoAnuncio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_empresa", nullable = false)
    private Empresa empresa;

}
