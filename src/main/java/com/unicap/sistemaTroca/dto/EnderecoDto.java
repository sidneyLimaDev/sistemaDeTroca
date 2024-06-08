package com.unicap.sistemaTroca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDto(

        @NotBlank
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve seguir o seguinte padrão: 11111-22")
        String cep,

        @NotBlank
        String rua,

        @NotBlank
        String bairro,

        @NotBlank
        String cidade,

        @NotBlank
        String estado,

        @NotBlank
        String pais,

        Integer numero
) {
}
