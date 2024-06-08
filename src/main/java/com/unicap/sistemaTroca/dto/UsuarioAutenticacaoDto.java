package com.unicap.sistemaTroca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.aspectj.weaver.ast.Not;

public record UsuarioAutenticacaoDto(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha
) {
}
