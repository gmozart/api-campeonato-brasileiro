package com.campeonato.apicampeonatobrasileiro.repository;


import com.campeonato.apicampeonatobrasileiro.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer> {

}
