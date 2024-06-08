package com.unicap.sistemaTroca.repositories;

import com.unicap.sistemaTroca.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);
}