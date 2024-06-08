package com.unicap.sistemaTroca.controllers.exceptions;

import com.unicap.sistemaTroca.exceptions.ApiCepException;
import com.unicap.sistemaTroca.exceptions.BancoDeDadosException;
import com.unicap.sistemaTroca.exceptions.ObjetoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> objetoNaoEncontrado(ObjetoNaoEncontradoException e, HttpServletRequest requisicao) {
        String error = "Objeto não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroPadrao erroPadrao = new ErroPadrao(ZonedDateTime.now(ZoneOffset.UTC).withZoneSameInstant(ZoneId.of("America/Sao_Paulo")), status.value(), error, e.getMessage(), requisicao.getRequestURI());
        return ResponseEntity.status(status).body(erroPadrao);
    }

    @ExceptionHandler(BancoDeDadosException.class)
    public ResponseEntity<ErroPadrao> bancoDeDadosException(BancoDeDadosException e, HttpServletRequest requisicao) {
        String error = "Erro no banco de Dados";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroPadrao erroPadrao = new ErroPadrao(ZonedDateTime.now(ZoneOffset.UTC).withZoneSameInstant(ZoneId.of("America/Sao_Paulo")), status.value(), error, e.getMessage(), requisicao.getRequestURI());
        return ResponseEntity.status(status).body(erroPadrao);
    }

    @ExceptionHandler({EntityNotFoundException.class, NoHandlerFoundException.class})
    public ResponseEntity tratarErro404(EntityNotFoundException entity, NoHandlerFoundException noHandler, HttpServletRequest requisicao) {
        String error = "Rota não encontrada";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroPadrao erroPadrao = new ErroPadrao(ZonedDateTime.now(ZoneOffset.UTC).withZoneSameInstant(ZoneId.of("America/Sao_Paulo")), status.value(), error, (entity != null) ? entity.getMessage() : noHandler.getMessage(), requisicao.getRequestURI());
        return ResponseEntity.status(status).body(erroPadrao);
    }

    @ExceptionHandler(ApiCepException.class)
    public ResponseEntity<ErroPadrao> apiCepException(ApiCepException e, HttpServletRequest request) {
        String error = "Erro na consulta do CEP na api do VIACEP";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroPadrao erroPadrao = new ErroPadrao(ZonedDateTime.now(ZoneOffset.UTC).withZoneSameInstant(ZoneId.of("America/Sao_Paulo")), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(erroPadrao);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErroPadrao> idNaoEncontrado(EmptyResultDataAccessException e, HttpServletRequest request) {
        String error = "O ID digitado não foi encontrado na base de dados";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroPadrao erroPadrao = new ErroPadrao(ZonedDateTime.now(ZoneOffset.UTC).withZoneSameInstant(ZoneId.of("America/Sao_Paulo")), status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(erroPadrao);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadrao> erroAoValidarOsCampos(MethodArgumentNotValidException e, HttpServletRequest request) {
        String error = "Erro ao validar campos recebidos";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroPadrao erroPadrao = new ErroPadrao(ZonedDateTime.now(ZoneOffset.UTC).withZoneSameInstant(ZoneId.of("America/Sao_Paulo")), status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(erroPadrao);
    }
}
