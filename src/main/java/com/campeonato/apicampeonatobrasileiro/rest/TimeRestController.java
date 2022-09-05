package com.campeonato.apicampeonatobrasileiro.rest;

import com.campeonato.apicampeonatobrasileiro.dto.TimeDTO;
import com.campeonato.apicampeonatobrasileiro.service.TimeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/times")
public class TimeRestController {

    @Autowired
    private TimeService timeServico;


    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Retorno dos times por Id")
    public ResponseEntity<TimeDTO> getTime(@PathVariable Integer id){
      return ResponseEntity.ok().body(timeServico.obterTime(id));
    }


    @GetMapping
    @ApiOperation(value = "Retorno dos times")
    public ResponseEntity<List <TimeDTO>>  getTimes(){
      return ResponseEntity.ok().body(timeServico.listarTimes());
    }

    @PostMapping
    public ResponseEntity<TimeDTO> cadastrarTime(@Valid @RequestBody TimeDTO time) throws Exception {
      return ResponseEntity.ok().body(timeServico.cadastrarTime(time));
    }

}
