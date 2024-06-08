package com.unicap.sistemaTroca.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.unicap.sistemaTroca.dto.EnderecoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false, nullable = true)
    private String cep;

    @Column(unique = false, nullable = true)
    private String rua;

    @Column(unique = false, nullable = true)
    private String bairro;

    @Column(unique = false, nullable = true)
    private String cidade;

    @Column(unique = false, nullable = true)
    private String estado;

    @Column(unique = false, nullable = true)
    private String pais;

    @Column(unique = false, nullable = true)
    private Integer numero;

    @OneToOne(mappedBy = "endereco")
    @JsonBackReference
    private Usuario usuario;

    public Endereco(EnderecoDto enderecoDto) {
        cep = enderecoDto.cep();
        rua = enderecoDto.rua();
        bairro = enderecoDto.bairro();
        cidade = enderecoDto.cidade();
        estado = enderecoDto.estado();
        pais = enderecoDto.pais();
        numero = enderecoDto.numero();
    }
}
