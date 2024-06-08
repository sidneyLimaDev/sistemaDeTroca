package com.unicap.sistemaTroca.models;

import com.unicap.sistemaTroca.dto.PessoaDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("PESSOA")
@Data
@NoArgsConstructor
public class Pessoa extends Usuario {

    @Column(unique = true, nullable = true)
    private String cpf;

    @Column(unique = false, nullable = true)
    private String sobrenome;

    @Column(unique = false, nullable = true)
    private String dataDeNascimento;

    public Pessoa(PessoaDto pessoaDto) {
        super(pessoaDto.nome(), pessoaDto.email(), pessoaDto.senha(), pessoaDto.celular(), pessoaDto.endereco());
        this.sobrenome = pessoaDto.sobrenome();
        this.cpf = pessoaDto.cpf();
        this.dataDeNascimento = pessoaDto.dataDeNascimento();
    }
}
