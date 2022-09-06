package com.campeonato.apicampeonatobrasileiro.dto;

import lombok.Data;

@Data
public class ClassificacaoDTO {

    private String time;
    private Integer idTime;
    private Integer posicao;
    private Integer pontos;
    private Integer jogos;
    private Integer vitorias;
    private Integer empates;
    private Integer derrotas;
    private Integer golsMarcados;
    private Integer golsSofridos;


}
