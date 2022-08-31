package com.campeonato.apicampeonatobrasileiro.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 20)
    private String nome;
    @Column(length = 2)
    private String uf;
    @Column(length = 4)
    private String sigla;
    private String estadio;

}
