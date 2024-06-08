package com.unicap.sistemaTroca.controllers;

import com.unicap.sistemaTroca.dto.OngDto;
import com.unicap.sistemaTroca.exceptions.BancoDeDadosException;
import com.unicap.sistemaTroca.models.Ong;
import com.unicap.sistemaTroca.models.Usuario;
import com.unicap.sistemaTroca.services.OngServico;
import com.unicap.sistemaTroca.services.UsuarioServico;
import com.unicap.sistemaTroca.utils.BCryptPassword;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/api/usuario/ong")
public class OngController {

    private UsuarioServico usuarioServico;

    private OngServico ongServico;

    @Autowired
    public OngController(UsuarioServico usuarioServico, OngServico ongServico) {
        this.usuarioServico = usuarioServico;
        this.ongServico = ongServico;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> criarOng(@RequestBody @Valid OngDto ongDto) {

        try {

            var ong = new Ong(ongDto);

            ong.setSenha(BCryptPassword.criptografarPassword(ong));
            var novaOng = usuarioServico.criar(ong);

            var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(novaOng.getId()).toUri();

            return ResponseEntity.created(uri).body(novaOng);

        }catch (DataIntegrityViolationException e) {
            throw new BancoDeDadosException("Campo(s) único(s) já existente(s) no banco de dados");
        }
    }

    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<Ong> atualizarDadosOng(@PathVariable Long id, @RequestBody OngDto ongDto) {
        var ong = new Ong(ongDto);

        if(ong.getSenha() != null) {
            ong.setSenha(BCryptPassword.criptografarPassword(ong));
        }

        var ongAtualizado = ongServico.atualizarOng(id, ong);

        return ResponseEntity.ok().body(ongAtualizado);
    }
}
