package com.unicap.sistemaTroca.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDto(
        @NotBlank
        String nome
) {
}
