package com.unicap.sistemaTroca.models.enums;

public enum TipoAnuncio {

    VENDA("Venda"),
    TROCA("Troca"),
    DOACAO("Doacao");

    private String valor;

    TipoAnuncio(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
