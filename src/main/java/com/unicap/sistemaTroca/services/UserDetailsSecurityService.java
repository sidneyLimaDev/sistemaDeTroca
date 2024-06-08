package com.unicap.sistemaTroca.services;

import com.unicap.sistemaTroca.data.UserDetailsSecurity;
import com.unicap.sistemaTroca.exceptions.ObjetoNaoEncontradoException;
import com.unicap.sistemaTroca.models.Usuario;
import com.unicap.sistemaTroca.repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsSecurityService implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepositorio.findByEmail(username);

        if(usuario.isEmpty()) {
            throw new ObjetoNaoEncontradoException("O usuário [" + username + "] não foi encontrado");
        }

        return new UserDetailsSecurity(usuario);
    }
}
