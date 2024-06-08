package com.unicap.sistemaTroca.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.unicap.sistemaTroca.data.UserDetailsSecurity;
import com.unicap.sistemaTroca.models.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTToken {

    @Value("${api.token.secret}")
    private String secret;

    public String gerarToken(UserDetailsSecurity usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);

            var token = JWT.create()
                    .withIssuer("api-sistemaTroca")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(obterExpiracaoToken())
                    .sign(algoritmo);

            return token;

        }catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar o token ", e);
        }

    }

    public String validarToken(String token) {
        var algoritmo = Algorithm.HMAC256(secret);

        try {
            return JWT.require(algoritmo)
                    .withIssuer("api-sistemaTroca")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant obterExpiracaoToken() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
