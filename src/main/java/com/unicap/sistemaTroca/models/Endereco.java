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
    private String logradouro;

    @Column(unique = false, nullable = true)
    private String bairro;

    @Column(unique = false, nullable = true)
    private String localidade;

    @Column(unique = false, nullable = true)
    private String uf;

    @Column(unique = false, nullable = true)
    private Integer numero;

    @OneToOne(mappedBy = "endereco")
    @JsonBackReference
    private Usuario usuario;

    public Endereco(EnderecoDto enderecoDto) {
        cep = enderecoDto.cep();
        logradouro = enderecoDto.logradouro();
        bairro = enderecoDto.bairro();
        localidade = enderecoDto.localidade();
        uf = enderecoDto.uf();
        numero = enderecoDto.numero();
    }
}
