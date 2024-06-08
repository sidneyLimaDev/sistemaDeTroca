package com.unicap.sistemaTroca.controllers;

import com.unicap.sistemaTroca.dto.AnuncioDto;
import com.unicap.sistemaTroca.models.Anuncio;
import com.unicap.sistemaTroca.services.AnuncioServico;
import com.unicap.sistemaTroca.services.CategoriaServico;
import com.unicap.sistemaTroca.services.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @PostMapping(value = "/criar/{idCategoria}/{idUsuario}")
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
}
