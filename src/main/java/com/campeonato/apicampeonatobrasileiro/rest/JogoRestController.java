package com.campeonato.apicampeonatobrasileiro.rest;

import com.campeonato.apicampeonatobrasileiro.dto.JogoDTO;
import com.campeonato.apicampeonatobrasileiro.entity.Jogo;
import com.campeonato.apicampeonatobrasileiro.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/jogos")
public class JogoRestController {

    @Autowired
    private JogoService jogoService;

    @PostMapping(value = "/gerar-jogos")
    public ResponseEntity gerarJogos(){
        jogoService.gerarJogos(LocalDateTime.now());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<JogoDTO>> obterJogos(){

        return ResponseEntity.ok().body(jogoService.obterJogos());
    }

    @PostMapping(value = "/finalizar/{id}")
    public ResponseEntity<JogoDTO> finalizarJogo(@RequestBody JogoDTO jogoDto){

        return ResponseEntity.ok().body(jogoService.finalizar(jogoDto));
    }
    @GetMapping(value="/classificacao")
    public ResponseEntity<JogoDTO> classificacao(){

        return ResponseEntity.ok().body(jogoService.obterClassificacao());
    }

   @GetMapping(value="/jogo/{id}")
    public ResponseEntity<JogoDTO> obterJogo(@PathVariable Integer id){

        return ResponseEntity.ok().body(jogoService.obterJogos(id));
    }
}
