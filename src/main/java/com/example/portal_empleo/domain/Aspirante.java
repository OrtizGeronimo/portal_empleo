package com.example.portal_empleo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    private Integer edad;

    private Integer dni;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "aspirante_skill",
            joinColumns = @JoinColumn(name = "aspirante_fk"),
            inverseJoinColumns = @JoinColumn(name = "habilidad_fk")
    )
    private List<Habilidad> habilidades = new ArrayList<Habilidad>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "aspirante_anuncio",
            joinColumns = @JoinColumn(name = "aspirante_fk"),
            inverseJoinColumns = @JoinColumn(name = "anuncio_fk")
    )
    private List<Anuncio> anuncios = new ArrayList<Anuncio>();
}
