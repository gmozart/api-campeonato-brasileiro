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
           Integer newId = Math.toIntExact(repository.count()+1);
           entity =  repository.save(entity);
           return toDto(entity);
        }else {
            throw new Exception("Time já Existe.");
        }
    }

    private Time toEntity(TimeDTO time) {
        Time entity = new Time();
        entity.setId(time.getId());
        entity.setEstadio(time.getEstadio());
        entity.setSigla(time.getSigla());
        entity.setUf(time.getUf());
        entity.setNome(time.getNome());
        return entity;
    }
    public TimeDTO toDto(Time entity) {
        TimeDTO dto = new TimeDTO();
        dto.setId(entity.getId());
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

    public List<Time> findAll() {
        return repository.findAll();
    }
}
