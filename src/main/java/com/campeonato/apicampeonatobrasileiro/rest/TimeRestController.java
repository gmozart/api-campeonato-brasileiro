package com.campeonato.apicampeonatobrasileiro.rest;

import com.campeonato.apicampeonatobrasileiro.entity.Time;
import com.campeonato.apicampeonatobrasileiro.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/times")
public class TimeRestController {

    @Autowired
    private TimeService timeServico;

    @GetMapping(value = "{id}")
    public ResponseEntity<Time> getTime(@PathVariable Integer id){
      return ResponseEntity.ok().body(timeServico.obterTime(id));
    }

    @GetMapping(value = "{}")
    public ResponseEntity<List <Time>>  getTimes(){
      return ResponseEntity.ok().body(timeServico.listarTimes());
    }

    public ResponseEntity<Void> cadastrarTime(Time time){
        timeServico.cadastrarTime(time);
      return ResponseEntity.ok().build();
    }

}
