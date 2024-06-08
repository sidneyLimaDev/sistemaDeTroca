package com.unicap.sistemaTroca.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record OngDto(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha,

        @Pattern(regexp = "^\\(\\d{2}\\)9\\d{4}-\\d{4}$", message = "O telefone deve ser: " +
                "No formato: XXXXXXXXXXX (Apenas números)                                                    +" +
                "Devem ser inseridos 11 números (DDD, 9 na frente e o número em si)")
        String celular,

        @Valid
        EnderecoDto endereco,

        @NotBlank
        @Pattern(regexp = "^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$", message = "O CNPJ deve estar no seguinte padrão: XX.XXX.XXX/0001-XX")
        String cnpj

) {
}
