package com.unicap.sistemaTroca.controllers;

import com.unicap.sistemaTroca.models.Endereco;
import com.unicap.sistemaTroca.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/endereco")
public class EnderecoController {

    private EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<Endereco> buscarEnderecoPorCep(@PathVariable String cep) {
        Endereco endereco = enderecoService.buscarEndereco(cep);

        return ResponseEntity.ok().body(endereco);
    }
}
