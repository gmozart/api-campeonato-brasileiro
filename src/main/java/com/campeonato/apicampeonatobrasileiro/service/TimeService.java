package com.campeonato.apicampeonatobrasileiro.service;

import com.campeonato.apicampeonatobrasileiro.entity.Time;
import com.campeonato.apicampeonatobrasileiro.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {

    @Autowired
    private TimeRepository repository;

    public Time obterTime(Integer id){
        return repository.findById(id).get();
    }
    public List<Time> listarTimes(){
        return repository.findAll();
    }

    public void cadastrarTime(Time time){
        repository.save(time);
    }


}
