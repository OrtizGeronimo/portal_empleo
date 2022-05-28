package com.example.portal_empleo.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "empresa")
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String razonSocial;
    private String cuil;
    private int telefono;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "empresa")
    private List<Anuncio> anuncios;

}
