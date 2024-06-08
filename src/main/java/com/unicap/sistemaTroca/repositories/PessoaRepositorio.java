package com.unicap.sistemaTroca.repositories;

import com.unicap.sistemaTroca.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa, Long> {
}
