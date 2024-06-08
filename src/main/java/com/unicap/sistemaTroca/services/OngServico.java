package com.unicap.renovo.services;

import com.unicap.renovo.exceptions.ObjetoNaoEncontradoException;
import com.unicap.renovo.models.Ong;
import com.unicap.renovo.repositories.EnderecoRepositorio;
import com.unicap.renovo.repositories.OngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OngServico {

    private OngRepository ongDao;
    private EnderecoRepositorio enderecoDao;

    @Autowired
    public OngServico(OngRepository ongDao) {
        this.ongDao = ongDao;
    }

    public Ong atualizarOng(Long id, Ong ongNovo) {
        Optional<Ong> ongAntigo = ongDao.findById(id);

        if(ongAntigo.isEmpty()) {
            throw new ObjetoNaoEncontradoException("A ONG com o id " + id + " não foi encontrado");
        }

        if(ongNovo.getEndereco() != null) {
            ongNovo.getEndereco().setId(ongAntigo.get().getEndereco().getId());
        }

        var ongAtualizado = atualizarDadosOng(ongAntigo.get(), ongNovo);

        return ongDao.save(ongAtualizado);
    }

    private Ong atualizarDadosOng(Ong ongAntigo, Ong ongNovo) {
        ongAntigo.setCelular((ongNovo.getCelular() != null) ? ongNovo.getCelular() : ongAntigo.getCelular());
        ongAntigo.setNome((ongNovo.getNome() != null) ? ongNovo.getNome() : ongAntigo.getNome());
        ongAntigo.setEmail((ongNovo.getEmail() != null) ? ongNovo.getEmail() : ongAntigo.getEmail());
        ongAntigo.setEndereco((ongNovo.getEndereco() != null) ? ongNovo.getEndereco() : ongAntigo.getEndereco());
        ongAntigo.setSenha((ongNovo.getSenha() != null) ? ongNovo.getSenha() : ongAntigo.getSenha());

        return ongAntigo;
    }
}
