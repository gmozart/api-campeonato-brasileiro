package com.campeonato.apicampeonatobrasileiro.dto;

import lombok.Data;

@Data
public class TimeDTO {

    private Integer id;
    private String nome;
    private String sigla;
    private String uf;
    private String estadio;
}
