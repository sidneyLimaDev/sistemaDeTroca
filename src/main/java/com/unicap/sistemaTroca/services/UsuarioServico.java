package com.unicap.sistemaTroca.services;

import com.unicap.sistemaTroca.exceptions.BancoDeDadosException;
import com.unicap.sistemaTroca.exceptions.ObjetoNaoEncontradoException;
import com.unicap.sistemaTroca.models.Usuario;
import com.unicap.sistemaTroca.repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepositorio usuarioDao;

    public Usuario criar(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioDao.findById(id);

        return usuario.orElseThrow(() -> new ObjetoNaoEncontradoException("Usuário não encontrado id: [" + id + "]"));
    }


    public List<Usuario> buscarTodos() {
        return usuarioDao.findAll();
    }

    public void deletarUsuario(Long id) {
        try {
            usuarioDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ObjetoNaoEncontradoException("O ID: [" + id + "] do usuário não foi encontrado.");
        } catch (DataIntegrityViolationException e) {
            throw new BancoDeDadosException(e.getMessage());
        }
    }

}
