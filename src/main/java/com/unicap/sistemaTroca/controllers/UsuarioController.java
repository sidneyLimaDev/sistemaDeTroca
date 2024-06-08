package com.unicap.sistemaTroca.controllers;

import com.unicap.sistemaTroca.dto.OngDto;
import com.unicap.sistemaTroca.exceptions.BancoDeDadosException;
import com.unicap.sistemaTroca.models.Ong;
import com.unicap.sistemaTroca.models.Usuario;
import com.unicap.sistemaTroca.services.UsuarioServico;
import com.unicap.sistemaTroca.utils.BCryptPassword;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {


    @Autowired
    private UsuarioServico usuarioServico;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        var usuario = usuarioServico.buscarPorId(id);

        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        var usuarios = usuarioServico.buscarTodos();

        return ResponseEntity.ok().body(usuarios);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioServico.deletarUsuario(id);

        return ResponseEntity.noContent().build();
    }
}
