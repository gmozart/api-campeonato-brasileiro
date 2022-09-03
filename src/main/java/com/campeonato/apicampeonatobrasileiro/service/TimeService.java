package com.campeonato.apicampeonatobrasileiro.service;

import com.campeonato.apicampeonatobrasileiro.dto.TimeDTO;
import com.campeonato.apicampeonatobrasileiro.entity.Time;
import com.campeonato.apicampeonatobrasileiro.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeService {

    @Autowired
    private TimeRepository repository;

    public TimeDTO cadastrarTime(TimeDTO time) throws Exception {
        Time entity = toEntity(time);
        if(time.getId() == null){
           entity =  repository.save(entity);
           return toDto(entity);
        }else {
            throw new Exception("Time já Existe.");
        }
    }

    private Time toEntity(TimeDTO time) {
        Time entity = new Time();
        entity.setEstadio(time.getEstadio());
        entity.setSigla(time.getSigla());
        entity.setUf(time.getUf());
        entity.setNome(time.getNome());
        return entity;
    }
    private TimeDTO toDto(Time entity) {
        TimeDTO dto = new TimeDTO();
        dto.setEstadio(entity.getEstadio());
        dto.setSigla(entity.getSigla());
        dto.setUf(entity.getUf());
        dto.setNome(entity.getNome());
        return dto;
    }

    public List<TimeDTO> listarTimes(){

        return repository.findAll().stream().map(entity -> toDto(entity)).collect(Collectors.toList());
    }
    public TimeDTO obterTime(Integer id){
        return toDto(repository.findById(id).get());
    }

}
