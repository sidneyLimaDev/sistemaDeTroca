package com.unicap.sistemaTroca.services;

import com.unicap.sistemaTroca.exceptions.ObjetoNaoEncontradoException;
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

        if(pessoa.isEmpty()) {
            throw new ObjetoNaoEncontradoException("O usuário com o id " + id + " não foi encotnrado");
        }

        if(obj.getEndereco() != null) {
            obj.getEndereco().setId(pessoa.get().getEndereco().getId());
        }

        Pessoa usuarioAtualizado = atualizarDadosPessoa(obj, pessoa.get());

        return pessoaDao.save(usuarioAtualizado);
    }

    public Pessoa atualizarDadosPessoa(Pessoa obj, Pessoa pessoa) {
        pessoa.setNome((obj.getNome() != null) ? obj.getNome() : pessoa.getNome());
        pessoa.setCelular((obj.getCelular() != null) ? obj.getCelular() : pessoa.getCelular());
        pessoa.setEmail((obj.getEmail() != null) ? obj.getEmail() : pessoa.getEmail());
        pessoa.setDataDeNascimento((obj.getDataDeNascimento() != null) ? obj.getDataDeNascimento() : pessoa.getDataDeNascimento());
        pessoa.setSenha((obj.getSenha() != null) ? obj.getSenha() : pessoa.getSenha());
        pessoa.setEndereco((obj.getEndereco() != null) ? obj.getEndereco() : pessoa.getEndereco());
        pessoa.setSobrenome((obj.getSobrenome() != null) ? obj.getSobrenome() : pessoa.getSobrenome());

        return pessoa;
    }
}
