package com.campeonato.apicampeonatobrasileiro.repository;


import com.campeonato.apicampeonatobrasileiro.entity.Jogo;
import com.campeonato.apicampeonatobrasileiro.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer> {

    //SELECT * FROM JOGO TIME = :TIME1 AND ENCERRADO = :EMCERRADO
    List<Jogo> findByTime1AndEncerrado(Time time1, Boolean encerrado);
    List<Jogo> findByTime2AndEncerrado(Time time2, Boolean encerrado);
    List<Jogo> findByEncerrado(Time time1, Boolean encerrado);


}
