package com.unicap.sistemaTroca.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicap.sistemaTroca.dto.CategoriaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Anuncio> anuncios;

    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Categoria(CategoriaDto categoriaDto) {
        this.nome = categoriaDto.nome();
    }
}
