package com.unicap.sistemaTroca.services;

import com.unicap.sistemaTroca.exceptions.ObjetoNaoEncontradoException;
import com.unicap.sistemaTroca.models.Categoria;
import com.unicap.sistemaTroca.repositories.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServico {

    private CategoriaRepositorio categoriaDao;

    @Autowired
    public CategoriaServico(CategoriaRepositorio categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

    public Categoria criar(Categoria categoria) {
       return categoriaDao.save(categoria);
    }

    public Categoria buscarPorId(Long id) {
        Optional<Categoria> categoria = categoriaDao.findById(id);

        return categoria.orElseThrow(() -> new ObjetoNaoEncontradoException("A categoria com o id " + id + " não foi encontrada."));
    }

    public Categoria buscarPorNome(String nome) {
        Optional<Categoria> categoria = categoriaDao.findByNome(nome);

        return categoria.orElseThrow(() -> new ObjetoNaoEncontradoException("A categoria com o nome " + nome + " não foi encontrada."));
    }

    public List<Categoria> buscarTodasCategorias() {
        List<Categoria> categorias = categoriaDao.findAll();

        return categorias;
    }

    public Categoria atualizarNomeCategoria(Long id, String nome) {
        Optional<Categoria> categoria = categoriaDao.findById(id);

        if(categoria.isEmpty()) {
            throw new ObjetoNaoEncontradoException("A categoria com o id: " + id + " não foi encontrada");
        }

        var categoriaAtualizada = atualizarDadosCategoria(nome, categoria.get());

        return categoriaDao.save(categoriaAtualizada);
    }

    private Categoria atualizarDadosCategoria(String nome, Categoria categoria) {
        categoria.setNome(nome);

        return categoria;
    }
}
