package com.unicap.sistemaTroca.services;

import com.unicap.sistemaTroca.exceptions.BancoDeDadosException;
import com.unicap.sistemaTroca.exceptions.ObjetoNaoEncontradoException;
import com.unicap.sistemaTroca.models.Anuncio;
import com.unicap.sistemaTroca.models.enums.StatusAnuncio;
import com.unicap.sistemaTroca.repositories.AnuncioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnuncioServico {

    private AnuncioRepositorio anuncioDao;

    @Autowired
    public AnuncioServico(AnuncioRepositorio anuncioDao) {
        this.anuncioDao = anuncioDao;
    }

    public Anuncio criar(Anuncio anuncio) {
        return anuncioDao.save(anuncio);
    }

    public void deletarAnuncio(Long id) {
        try {
            anuncioDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ObjetoNaoEncontradoException("O ID: [" + id + "] do usuário não foi encontrado.");
        } catch (DataIntegrityViolationException e) {
            throw new BancoDeDadosException(e.getMessage());
        }
    }

    public Anuncio buscarAnuncioPorId(Long id) {
        Optional<Anuncio> anuncio = anuncioDao.findById(id);

        return anuncio.orElseThrow(() -> new ObjetoNaoEncontradoException("O Anúncio com ID: " + id + " não foi encontrado"));
    }

    public List<Anuncio> buscarAnuncioPorNome(String nome) {
        List<Anuncio> anuncio = anuncioDao.buscarPorNome(nome);
        if(anuncio.isEmpty()) {
            throw new ObjetoNaoEncontradoException("Não foi encontrado nenhum anúncio com o nome: \"" + nome + "\"");
        }

        return anuncio;
    }

    public List<Anuncio> buscarTodosAnuncios() {
        return anuncioDao.findAll();
    }

    public Anuncio atualizarAnuncio(Long id, Anuncio novo) {
        Optional<Anuncio> anuncioAntigo = anuncioDao.findById(id);

        if(anuncioAntigo.isEmpty()) {
           throw new ObjetoNaoEncontradoException("O Anúncio com ID " + id + " não foi encontrado");
        }

        Anuncio anuncioAtualizado = atualizarDadosAnuncio(anuncioAntigo.get(), novo);

        return anuncioDao.save(anuncioAtualizado);
    }

    public Anuncio atualizarStatusAnuncio(Long id, StatusAnuncio statusAnuncio) {
        Optional<Anuncio> anuncioAntigo = anuncioDao.findById(id);

        if(anuncioAntigo.isEmpty()) {
            throw new ObjetoNaoEncontradoException("O Anúncio com ID " + id + " não foi encontrado");
        }

        anuncioAntigo.get().setStatusAnuncio(statusAnuncio);
        return anuncioDao.save(anuncioAntigo.get());
    }

    private Anuncio atualizarDadosAnuncio(Anuncio anuncioAntigo, Anuncio novo) {
        anuncioAntigo.setPreco(novo.getPreco());
        anuncioAntigo.setDescricao(novo.getDescricao());
        anuncioAntigo.setUrlImagens(novo.getUrlImagens());
        anuncioAntigo.setCategoria(novo.getCategoria());
        anuncioAntigo.setEstadoProduto(novo.getEstadoProduto());
        anuncioAntigo.setEstadoProduto(novo.getEstadoProduto());

        return anuncioAntigo;
    }
}
