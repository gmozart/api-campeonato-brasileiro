package com.campeonato.apicampeonatobrasileiro.service;

import com.campeonato.apicampeonatobrasileiro.entity.Jogo;
import com.campeonato.apicampeonatobrasileiro.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    @Autowired
    private JogoRepository repository;


    public List<Jogo> listarJogos(){
        return repository.findAll();
    }

}
