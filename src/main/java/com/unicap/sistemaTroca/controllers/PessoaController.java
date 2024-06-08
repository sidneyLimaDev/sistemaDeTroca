package com.unicap.sistemaTroca.controllers;

import com.unicap.sistemaTroca.dto.PessoaDto;
import com.unicap.sistemaTroca.exceptions.BancoDeDadosException;
import com.unicap.sistemaTroca.models.Pessoa;
import com.unicap.sistemaTroca.models.Usuario;
import com.unicap.sistemaTroca.services.PessoaServico;
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
@RequestMapping(value = "/api/usuario/pessoa")
public class PessoaController {

    private PessoaServico pessoaServico;

    private UsuarioServico usuarioServico;

    @Autowired
    public PessoaController(PessoaServico pessoaServico, UsuarioServico usuarioServico) {
        this.pessoaServico = pessoaServico;
        this.usuarioServico = usuarioServico;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> criarPessoa(@RequestBody @Valid PessoaDto pessoaDto) {

        try {

            var pessoa = new Pessoa(pessoaDto);

            pessoa.setSenha(BCryptPassword.criptografarPassword(pessoa));
            var novaPessoa = usuarioServico.criar(pessoa);

            var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(novaPessoa.getId()).toUri();

            return ResponseEntity.created(uri).body(novaPessoa);

        }catch (DataIntegrityViolationException e) {
            throw new BancoDeDadosException("Campo(s) único(s) já existente(s) no banco de dados");
        }
    }

    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<Usuario> atualizarDadosPessoa(@PathVariable Long id, @RequestBody @Valid PessoaDto pessoaDto) {
        Pessoa obj = new Pessoa(pessoaDto);

        if(obj.getSenha() != null) {
            obj.setSenha(BCryptPassword.criptografarPassword(obj));
        }

        Usuario usuario = pessoaServico.atualizarPessoa(id, obj);

        return ResponseEntity.ok().body(usuario);
    }
}
