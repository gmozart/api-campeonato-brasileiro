package com.campeonato.apicampeonatobrasileiro.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClassificacaoTimeDTO {

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
