package com.unicap.sistemaTroca.controllers;

import com.unicap.sistemaTroca.dto.CategoriaDto;
import com.unicap.sistemaTroca.models.Categoria;
import com.unicap.sistemaTroca.services.CategoriaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/api/categoria")
public class CategoriaController {

    private CategoriaServico categoriaServico;

    @Autowired
    public CategoriaController(CategoriaServico categoriaServico) {
        this.categoriaServico = categoriaServico;
    }

    @PostMapping
    public ResponseEntity<Categoria> criar(@RequestBody CategoriaDto categoria) {
        var cat = new Categoria(categoria);
        Categoria obj = categoriaServico.criar(cat);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> buscarTodasCategoria() {
        List<Categoria> categorias = categoriaServico.buscarTodasCategorias();

        return ResponseEntity.ok().body(categorias);
    }

    @GetMapping(value = "/{nome}")
    public ResponseEntity<Categoria> buscarPorNome(@PathVariable String nome) {
        var categoria = categoriaServico.buscarPorNome(nome);

        return ResponseEntity.ok().body(categoria);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Categoria> atualizarNomeCategoria(@PathVariable Long id, @RequestBody CategoriaDto categoriaDto) {
        var categoria = categoriaServico.atualizarNomeCategoria(id, categoriaDto.nome());

        return ResponseEntity.ok().body(categoria);
    }
}
