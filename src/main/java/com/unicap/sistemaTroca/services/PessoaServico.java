package com.unicap.sistemaTroca.services;

import com.unicap.sistemaTroca.models.Pessoa;
import com.unicap.sistemaTroca.repositories.PessoaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaServico {

    private PessoaRepositorio pessoaDao;

    @Autowired
    public PessoaServico(PessoaRepositorio pessoaDao) {
        this.pessoaDao = pessoaDao;
    }

    public Pessoa atualizarPessoa(Long id, Pessoa obj) {

        Optional<Pessoa> pessoa = pessoaDao.findById(id);
        Pessoa usuarioAtualizado = atualizarDadosPessoa(obj, pessoa.get());

        return pessoaDao.save(usuarioAtualizado);
    }

    public Pessoa atualizarDadosPessoa(Pessoa obj, Pessoa pessoa) {
        pessoa.setNome(obj.getNome());
        pessoa.setCelular(obj.getCelular());
        pessoa.setEmail(obj.getEmail());
        pessoa.setDataDeNascimento(obj.getDataDeNascimento());

        return pessoa;
    }
}
