package com.campeonato.apicampeonatobrasileiro.service;

import com.campeonato.apicampeonatobrasileiro.entity.Jogo;
import com.campeonato.apicampeonatobrasileiro.entity.Time;
import com.campeonato.apicampeonatobrasileiro.repository.JogoRepository;
import com.campeonato.apicampeonatobrasileiro.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    TimeService timeServico;

    /**
     * @param primeiraRodada Data da primeira Rodada
     *
     */

    public void gerarJogos(LocalDateTime primeiraRodada){
        final List<Time> times = timeServico.findAll();
        List<Time> all1 = new ArrayList<>();
        List<Time> all2 = new ArrayList<>();
        all1.addAll(times);
        all2.addAll(times);

        jogoRepository.deleteAll();

        List<Jogo> jogos = new ArrayList<>();

        int t = times.size();     // t recebe a quantidade máxima dos times
        int m = times.size() / 2;  // m receve a quantidade máxima dos times e divide por 2
        LocalDateTime dataJogo = primeiraRodada;
        Integer rodada = 0;
        for(int i = 0 ;i < t - 1; i++){ //Inicio da primeira rodada
            rodada = i + 1;
            for(int j = 0; j < m ; j++ ){
                //teste para ajustar o mando de campo
                Time time1;
                Time time2;
                if(j % 2 == 1 || i % 2 == 1 && j == 0){
                    time1 = times.get(t - j - 1);
                    time2 = times.get(j);
                } else {
                    time1 = times.get(j);
                    time2 = times.get(t - j -1);
                }
                if (time1 == null){
                    System.out.println("Time 1 null");
                }
                jogos.add(gerarJogos(dataJogo, rodada, time1, time2));
                dataJogo = dataJogo.plusDays(7);
            }
            //Gira os times no sentido horário, mantendo o primeiro lugar
            times.add(1, times.remove(times.size() - 1));
        }

        jogos.forEach(jogo -> System.out.println(jogo));

        jogoRepository.saveAll(jogos);

        List<Jogo> jogos2 = new ArrayList<>();

        jogos.forEach(jogo ->{
            Time time1 = jogo.getTime2();
            Time time2 = jogo.getTime1();
            jogos2.add(gerarJogos(jogo.getData().plusDays(7 * jogos.size()), jogo.getRodada() + jogos.size(), time1, time2));
        });
        jogoRepository.saveAll(jogos2);
    }

    private Jogo gerarJogos(LocalDateTime dataJogo, Integer rodada, Time time1, Time time2) {
        Jogo jogo = new Jogo();

        jogo.setTime1(time1);
        jogo.setTime2(time2);
        jogo.setRodada(rodada);
        jogo.setData(dataJogo);
        jogo.setEncerrado(false);
        jogo.setGolsTime1(0);
        jogo.setGolsTime2(0);
        jogo.setPublicoPagante(0);
        return jogo;

    }

    public List<Jogo> obterJogos() {
     return jogoRepository.findAll();
    }
}
