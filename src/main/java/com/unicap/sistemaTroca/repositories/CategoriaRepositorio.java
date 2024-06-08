package com.unicap.sistemaTroca.repositories;

import com.unicap.sistemaTroca.models.Anuncio;
import com.unicap.sistemaTroca.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNome(String nome);

}
