package com.unicap.sistemaTroca.dto;

import com.unicap.sistemaTroca.models.enums.EstadoProduto;
import com.unicap.sistemaTroca.models.enums.TipoAnuncio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record AnuncioDto(

        @NotBlank
        @Valid
        TipoAnuncio tipoAnuncio,

        @NotBlank
        String descricao,

        List<String> urlImagens,

        @Valid
        EstadoProduto estadoProduto,

        @NotBlank
        Double preco,

        String categoria,

        @NotBlank
        String nome

) {
}
