package com.unicap.sistemaTroca.services;

import com.unicap.sistemaTroca.models.Anuncio;
import com.unicap.sistemaTroca.repositories.AnuncioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnuncioServico {

    private AnuncioRepositorio anuncioDao;

    @Autowired
    public AnuncioServico(AnuncioRepositorio anuncioDao) {
        this.anuncioDao = anuncioDao;
    }

    public Anuncio criar(Anuncio anuncio) {
        return anuncioDao.save(anuncio);
    }


}
