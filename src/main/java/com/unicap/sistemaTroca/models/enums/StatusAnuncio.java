package com.unicap.sistemaTroca.models.enums;

public enum StatusAnuncio {

    CONCLUIDO("Concluído"),
    ANDAMENTO("Andamento"),
    BLOQUEADO("Bloqueado");

    private String valor;

    StatusAnuncio(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
