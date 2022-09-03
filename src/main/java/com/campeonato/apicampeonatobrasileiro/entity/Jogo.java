package com.campeonato.apicampeonatobrasileiro.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Jogo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Integer golsTime1;
  private Integer golsTime2;
  private Integer publicoPagante;
  private LocalDateTime data;
  private Integer rodada;

  @ManyToOne
  @JoinColumn(name="time1")
  private Time time1;

  @ManyToOne
  @JoinColumn(name="time2")
  private Time time2;




}
