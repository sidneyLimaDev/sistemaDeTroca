package com.unicap.sistemaTroca.repositories;

import com.unicap.sistemaTroca.models.Categoria;
import com.unicap.sistemaTroca.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT p FROM Usuario p WHERE p.nome LIKE %:nome%")
    List<Usuario> buscarPorNome(String nome);
}
