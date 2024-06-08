package com.unicap.sistemaTroca.utils;

import com.unicap.sistemaTroca.models.Usuario;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptPassword {

    // vai de 4 à 31 (o padrão do gensalt() é 10)
    private static final int complexidadeSenha = 10;

    public static String criptografarPassword(Usuario usuario) {
        return BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt(complexidadeSenha));
    }
}
