package com.campeonato.apicampeonatobrasileiro.rest;

import com.campeonato.apicampeonatobrasileiro.entity.Time;
import com.campeonato.apicampeonatobrasileiro.service.TimeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/times")
public class TimeRestController {

    @Autowired
    private TimeService timeServico;

    @ApiOperation(value = "Obtém os dados de um time")
    @GetMapping(value = "{id}")
    public ResponseEntity<Time> getTime(@PathVariable Integer id){
      return ResponseEntity.ok().body(timeServico.obterTime(id));
    }

    @ApiOperation(value = "Obtém uma lista de Times")
    @GetMapping(value = "{}")
    public ResponseEntity<List <Time>>  getTimes(){
      return ResponseEntity.ok().body(timeServico.listarTimes());
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarTime(Time time){
        timeServico.cadastrarTime(time);
      return ResponseEntity.ok().build();
    }

}
