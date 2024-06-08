package com.unicap.sistemaTroca.controllers;

import com.unicap.sistemaTroca.dto.CategoriaDto;
import com.unicap.sistemaTroca.models.Categoria;
import com.unicap.sistemaTroca.services.CategoriaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/categoria")
public class CategoriaController {

    private CategoriaServico categoriaServico;

    @Autowired
    public CategoriaController(CategoriaServico categoriaServico) {
        this.categoriaServico = categoriaServico;
    }

    @PostMapping(value = "/criar")
    public ResponseEntity<Categoria> criar(@RequestBody CategoriaDto categoria) {
        var cat = new Categoria(categoria);
        Categoria obj = categoriaServico.criar(cat);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).body(obj);
    }
}
