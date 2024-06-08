package com.unicap.sistemaTroca.models;

import com.unicap.sistemaTroca.dto.OngDto;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue("ONG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ong extends Usuario {

    private static final long serialVersionUID = 1L;

    @Column(unique = true, nullable = true)
    private String cnpj;

    public Ong(OngDto ongDto) {
        super(ongDto.nome(), ongDto.email(), ongDto.senha(), ongDto.celular(), ongDto.endereco());
        this.cnpj = ongDto.cnpj();
    }
}
