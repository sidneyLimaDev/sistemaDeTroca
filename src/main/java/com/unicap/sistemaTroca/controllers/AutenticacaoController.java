package com.unicap.sistemaTroca.controllers;

import com.unicap.sistemaTroca.data.UserDetailsSecurity;
import com.unicap.sistemaTroca.dto.LoginTokenResposta;
import com.unicap.sistemaTroca.dto.UsuarioAutenticacaoDto;
import com.unicap.sistemaTroca.security.JWTToken;
import com.unicap.sistemaTroca.services.UsuarioServico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/autenticacao")
public class AutenticacaoController {

    @Autowired
    private UsuarioServico usuarioServico;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTToken jwtToken;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioAutenticacaoDto usuarioAutenticacaoDto) {
        var usuarioEmailSenha = new UsernamePasswordAuthenticationToken(usuarioAutenticacaoDto.email(), usuarioAutenticacaoDto.senha());

        var auth = authenticationManager.authenticate(usuarioEmailSenha);

        var token = jwtToken.gerarToken((UserDetailsSecurity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginTokenResposta(usuarioAutenticacaoDto.email(), token));

    }
}
