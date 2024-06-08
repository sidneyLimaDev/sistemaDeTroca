package com.unicap.sistemaTroca.controllers.exceptions;

import com.unicap.sistemaTroca.exceptions.BancoDeDadosException;
import com.unicap.sistemaTroca.exceptions.ObjetoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> objetoNaoEncontrado(ObjetoNaoEncontradoException e, HttpServletRequest requisicao) {
        String error = "Objeto não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroPadrao erroPadrao = new ErroPadrao(Instant.now(), status.value(), error, e.getMessage(), requisicao.getRequestURI());
        return ResponseEntity.status(status).body(erroPadrao);
    }

    @ExceptionHandler(BancoDeDadosException.class)
    public ResponseEntity<ErroPadrao> bancoDeDadosException(BancoDeDadosException e, HttpServletRequest requisicao) {
        String error = "Erro no banco de Dados";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroPadrao erroPadrao = new ErroPadrao(Instant.now(), status.value(), error, e.getMessage(), requisicao.getRequestURI());
        return ResponseEntity.status(status).body(erroPadrao);
    }

    @ExceptionHandler({EntityNotFoundException.class, NoHandlerFoundException.class})
    public ResponseEntity tratarErro404(EntityNotFoundException entity, NoHandlerFoundException noHandler,  HttpServletRequest requisicao) {
        String error = "Rota não encontrada";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroPadrao erroPadrao = new ErroPadrao(Instant.now(), status.value(), error, (entity != null) ? entity.getMessage() : noHandler.getMessage(), requisicao.getRequestURI());
        return ResponseEntity.status(status).body(erroPadrao);
    }
}
