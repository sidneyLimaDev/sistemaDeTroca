package com.unicap.sistemaTroca.repositories;

import com.unicap.sistemaTroca.models.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepositorio extends JpaRepository<Anuncio, Long> {
}
