package com.unicap.sistemaTroca.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PessoaDto(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha,

        @Pattern(regexp = "^\\(\\d{2}\\)9\\d{4}-\\d{4}$", message = "O número de celular deve ser: " +
                "No formato: (dd)9xxxx-xxxx (Apenas números)                                                    +" +
                "Devem ser inseridos 11 números (DDD, 9 na frente e o número em si)")
        String celular,

        @Valid
        EnderecoDto endereco,

        @NotBlank
        @Pattern(regexp = "^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$", message = "O CPF deve estar no seguinte padrão: XXX.XXX.XXX-01")
        String cpf,

        @NotBlank
        String dataDeNascimento
) {

}
