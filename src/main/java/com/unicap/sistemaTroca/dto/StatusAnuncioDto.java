package com.unicap.sistemaTroca.dto;

import com.unicap.sistemaTroca.models.enums.StatusAnuncio;
import jakarta.validation.constraints.NotBlank;

public record StatusAnuncioDto(
        @NotBlank
        StatusAnuncio statusAnuncio
) {
}
