package com.unicap.sistemaTroca.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.unicap.sistemaTroca.dto.EnderecoDto;
import com.unicap.sistemaTroca.dto.PessoaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="usuario_type",
        discriminatorType = DiscriminatorType.STRING)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(unique = false, nullable = false)
    protected String nome;

    @Column(unique = true, nullable = false)
    protected String email;

    @Column(unique = false, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected String senha;

    @Column(unique = false, nullable = true)
    protected String celular;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    protected Endereco endereco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    protected List<Anuncio> anuncios = new ArrayList<>();

    public Usuario(String nome, String email, String senha, String celular, EnderecoDto enderecoDto) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.celular = celular;
        this.endereco = new Endereco(enderecoDto);
    }
}
