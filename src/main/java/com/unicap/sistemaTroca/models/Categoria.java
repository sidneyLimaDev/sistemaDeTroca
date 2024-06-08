package com.unicap.sistemaTroca.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicap.sistemaTroca.dto.CategoriaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false, nullable = false)
    private String nome;

    @OneToOne(mappedBy = "categoria")
    @JsonIgnore
    private Anuncio anuncio;

    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Categoria(CategoriaDto categoriaDto) {
        this.nome = categoriaDto.nome();
    }
}
