package com.unicap.sistemaTroca.repositories;

import com.unicap.sistemaTroca.models.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepositorio extends JpaRepository<Anuncio, Long> {

    @Query("SELECT p FROM Anuncio p WHERE p.nome LIKE %:nome%")
    List<Anuncio> buscarPorNome(String nome);
}
