package com.example.portal_empleo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@Table(name = "anuncios")
@NoArgsConstructor
@AllArgsConstructor

public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_empresa")
    private Empresa empresa;

}
