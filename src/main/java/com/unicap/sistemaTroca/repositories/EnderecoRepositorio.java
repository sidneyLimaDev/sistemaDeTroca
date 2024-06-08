package com.unicap.sistemaTroca.repositories;

import com.unicap.sistemaTroca.models.Categoria;
import com.unicap.sistemaTroca.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {

    Optional<Endereco> findByCep(String cep);

}
