package com.example.portal_empleo.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "habilidad")
@NoArgsConstructor
@AllArgsConstructor
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nombre;
    private Date fechaBaja;
    private String tipoHabilidad;


}

