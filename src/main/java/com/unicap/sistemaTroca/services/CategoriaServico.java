package com.unicap.sistemaTroca.services;

import com.unicap.sistemaTroca.models.Categoria;
import com.unicap.sistemaTroca.repositories.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return categoria.get();
    }
}
