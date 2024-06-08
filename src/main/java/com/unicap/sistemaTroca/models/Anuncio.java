package com.unicap.sistemaTroca.models;


import com.unicap.sistemaTroca.dto.AnuncioDto;
import com.unicap.sistemaTroca.models.enums.EstadoProduto;
import com.unicap.sistemaTroca.models.enums.StatusAnuncio;
import com.unicap.sistemaTroca.models.enums.TipoAnuncio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Anuncio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false, nullable = false)
    private ZonedDateTime momento;

    @Column(unique = false, nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(unique = false, nullable = false)
    private StatusAnuncio statusAnuncio;

    @Enumerated(EnumType.STRING)
    @Column(unique = false, nullable = false)
    private TipoAnuncio tipoAnuncio;

    @Column(unique = false, nullable = false)
    private String descricao;

    @Column(unique = false, nullable = true)
    private List<String> urlImagens;

    @Enumerated(EnumType.STRING)
    @Column(unique = false, nullable = false)
    private EstadoProduto estadoProduto;

    @Column(unique = false, nullable = false)
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Anuncio(AnuncioDto anuncioDto, Usuario usuario, Categoria categoria) {
        this.momento = ZonedDateTime.now(ZoneOffset.UTC).withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
        this.nome = anuncioDto.nome().toUpperCase();
        this.statusAnuncio = StatusAnuncio.ANDAMENTO;
        this.tipoAnuncio = anuncioDto.tipoAnuncio();
        this.descricao = anuncioDto.descricao();
        this.urlImagens = anuncioDto.urlImagens();
        this.estadoProduto = anuncioDto.estadoProduto();
        this.preco = anuncioDto.preco();
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public Anuncio(AnuncioDto anuncioDto, Categoria categoria) {
        this.momento = ZonedDateTime.now(ZoneOffset.UTC).withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
        this.nome = anuncioDto.nome();
        this.statusAnuncio = StatusAnuncio.ANDAMENTO;
        this.tipoAnuncio = anuncioDto.tipoAnuncio();
        this.descricao = anuncioDto.descricao();
        this.urlImagens = anuncioDto.urlImagens();
        this.estadoProduto = anuncioDto.estadoProduto();
        this.preco = anuncioDto.preco();
        this.categoria = categoria;
    }

}


