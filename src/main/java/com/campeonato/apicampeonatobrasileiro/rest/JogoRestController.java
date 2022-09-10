package com.campeonato.apicampeonatobrasileiro.rest;

import com.campeonato.apicampeonatobrasileiro.dto.ClassificacaoDTO;
import com.campeonato.apicampeonatobrasileiro.dto.JogoDTO;
import com.campeonato.apicampeonatobrasileiro.dto.JogoFinalizadoDTO;
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

        return ResponseEntity.ok().body(jogoService.listarJogos());
    }

    @PostMapping(value = "/finalizar/{id}")
    public ResponseEntity<JogoDTO> finalizarJogo(@PathVariable Integer id, @RequestBody JogoFinalizadoDTO jogoDto) throws Exception {

        return ResponseEntity.ok().body(jogoService.finalizar(id, jogoDto));
    }
    @GetMapping(value="/classificacao")
    public ResponseEntity<ClassificacaoDTO> classificacao(){

        return ResponseEntity.ok().body(jogoService.obterClassificacao());
    }

   @GetMapping(value="/jogo/{id}")
    public ResponseEntity<JogoDTO> obterJogo(@PathVariable Integer id){
        return ResponseEntity.ok().body(jogoService.jogoId(id));
    }
}
