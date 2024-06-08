package com.unicap.sistemaTroca.controllers;

import com.unicap.sistemaTroca.dto.AnuncioDto;
import com.unicap.sistemaTroca.dto.StatusAnuncioDto;
import com.unicap.sistemaTroca.models.Anuncio;
import com.unicap.sistemaTroca.services.AnuncioServico;
import com.unicap.sistemaTroca.services.CategoriaServico;
import com.unicap.sistemaTroca.services.UsuarioServico;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/api/anuncio")
public class AnuncioController {

    private AnuncioServico anuncioServico;

    private UsuarioServico usuarioServico;

    private CategoriaServico categoriaServico;

    @Autowired
    public AnuncioController(AnuncioServico anuncioServico, UsuarioServico usuarioServico, CategoriaServico categoriaServico) {
        this.anuncioServico = anuncioServico;
        this.usuarioServico = usuarioServico;
        this.categoriaServico = categoriaServico;
    }

    @GetMapping
    public ResponseEntity<List<Anuncio>> buscarTodosAnuncios() {
        var anuncios = anuncioServico.buscarTodosAnuncios();

        return ResponseEntity.ok().body(anuncios);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Anuncio> buscarAnuncioPorId(@PathVariable Long id) {
        var anuncio = anuncioServico.buscarAnuncioPorId(id);

        return ResponseEntity.ok().body(anuncio);
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<List<Anuncio>> buscarTodosAnunciosPorNome(@PathVariable String nome) {
        var anuncios = anuncioServico.buscarAnuncioPorNome(nome);

        return ResponseEntity.ok().body(anuncios);
    }

    @PostMapping(value = "/{idCategoria}/{idUsuario}")
    @Transactional
    public ResponseEntity<Anuncio> criar(@RequestBody AnuncioDto anuncioDto, @PathVariable Long idCategoria, @PathVariable Long idUsuario) {

        var categoria = categoriaServico.buscarPorId(idCategoria);
        var usuario = usuarioServico.buscarPorId(idUsuario);

        var anuncio = new Anuncio(anuncioDto, usuario, categoria);

        var anuncioSalvo = anuncioServico.criar(anuncio);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}")
                .buildAndExpand(anuncioSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(anuncioSalvo);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioServico.deletarUsuario(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<Anuncio> atualizarAnuncio(@PathVariable Long id, @RequestBody AnuncioDto anuncioDto) {

        var categoria = categoriaServico.buscarPorNome(anuncioDto.categoria());

        var anuncioNovo = new Anuncio(anuncioDto, categoria);

        var anuncioAtualizado = anuncioServico.atualizarAnuncio(id, anuncioNovo);

        return ResponseEntity.ok().body(anuncioAtualizado);

    }

    @PutMapping(value = "/status/{id}")
    public ResponseEntity<Anuncio> atualizarStatusAnuncio(@PathVariable Long id, @RequestBody StatusAnuncioDto statusAnuncioDto) {
        Anuncio anuncio = anuncioServico.atualizarStatusAnuncio(id, statusAnuncioDto.statusAnuncio());

        return ResponseEntity.ok().body(anuncio);
    }
}
