package com.example.portal_empleo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "aspirante")
@NoArgsConstructor
@AllArgsConstructor

public class Aspirante {

    /*
    Hay que ver como mapeamos los estados, como String o entidades aparte
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private String apellido;

    private int edad;

    private int dni;
}
