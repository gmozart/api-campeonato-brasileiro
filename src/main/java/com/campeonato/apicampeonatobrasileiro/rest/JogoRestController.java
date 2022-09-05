package com.campeonato.apicampeonatobrasileiro.rest;

import com.campeonato.apicampeonatobrasileiro.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class JogoRestController {

    @Autowired
    private JogoService jogoService;

    @PostMapping(value = "gerar-jogos")
    public ResponseEntity gerarJogos(){
        jogoService.gerarJogos(LocalDateTime.now());
        return ResponseEntity.ok().build();
    }

}
