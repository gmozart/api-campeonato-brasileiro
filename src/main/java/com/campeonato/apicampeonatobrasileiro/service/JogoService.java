package com.campeonato.apicampeonatobrasileiro.service;

import com.campeonato.apicampeonatobrasileiro.repository.JogoRepository;
import com.campeonato.apicampeonatobrasileiro.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class JogoService {

    @Autowired
    private JogoRepository repository;

    @Autowired
    TimeRepository timeRepository;

    /**
     * @param primeiraRodada Data da primeira Rodada
     * @param datasInvalidas Datas que não podem ter jogos (ex: Datas fifa)
     */

    public void gerarJogos(LocalDateTime primeiraRodada, List<LocalDate> datasInvalidas){
        final List<Time> times = timeService.findAll();
        List<Time> all1 = new ArrayList<>();
        List<Time> all2 = new ArrayList<>();

    }

}
