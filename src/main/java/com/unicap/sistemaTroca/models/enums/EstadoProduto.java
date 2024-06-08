package com.unicap.sistemaTroca.models.enums;

public enum EstadoProduto {
    NOVO("Novo"),
    USADO("Usado"),
    BOA_CONSERVACAO("Boa Conservação");

    private String valor;

    EstadoProduto(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
