package com.campeonato.apicampeonatobrasileiro.service;

import com.campeonato.apicampeonatobrasileiro.dto.ClassificacaoDTO;
import com.campeonato.apicampeonatobrasileiro.dto.ClassificacaoTimeDTO;
import com.campeonato.apicampeonatobrasileiro.dto.JogoDTO;
import com.campeonato.apicampeonatobrasileiro.dto.JogoFinalizadoDTO;
import com.campeonato.apicampeonatobrasileiro.entity.Jogo;
import com.campeonato.apicampeonatobrasileiro.entity.Time;
import com.campeonato.apicampeonatobrasileiro.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


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
        int m = times.size() / 2;  // m recebe a quantidade máxima dos times e divide por 2
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
        Random random = new Random();
        int gols1 = random.nextInt(7);
        int gols2 = random.nextInt(7);
        int publico = random.nextInt(35000)+5000;

        jogo.setTime1(time1);
        jogo.setTime2(time2);
        jogo.setRodada(rodada);
        jogo.setData(dataJogo);
        jogo.setEncerrado(false);
        jogo.setGolsTime1(gols1);
        jogo.setGolsTime2(gols2);
        jogo.setPublicoPagante(publico);
        return jogo;

    }

    private JogoDTO toDto(Jogo entity){
        JogoDTO dto = new JogoDTO();
        dto.setId(entity.getId());
        dto.setData(entity.getData());
        dto.setRodada(entity.getRodada());
        dto.setEncerrado(entity.getEncerrado());
        dto.setGolsTime1(entity.getGolsTime1());
        dto.setGolsTime2(entity.getGolsTime2());
        dto.setPublicoPagante(entity.getPublicoPagante());
        dto.setTime1(timeServico.toDto(entity.getTime1()));
        dto.setTime2(timeServico.toDto(entity.getTime2()));
        return dto;
    }

    public List<JogoDTO> listarJogos() {

        return jogoRepository.findAll().stream().map(entity -> toDto(entity)).collect(Collectors.toList());
    }

    public JogoDTO jogoId(Integer id) {

        return toDto(jogoRepository.findById(id).get());
    }


    public JogoDTO  finalizar(Integer id, JogoFinalizadoDTO jogoDTO) throws Exception {
      Optional<Jogo> optionalJogo = jogoRepository.findById(id);
      if (optionalJogo.isPresent()){
          final Jogo jogo = optionalJogo.get();
          jogo.setGolsTime1(jogoDTO.getGolsTime1());
          jogo.setGolsTime2(jogoDTO.getGolsTime2());
          jogo.setEncerrado(true);
          jogo.setPublicoPagante(jogoDTO.getPublicoPagante());
         return  toDto(jogoRepository.save(jogo));
      } else {
          throw new Exception("Jogo não existe");
      }
    }

    public ClassificacaoDTO obterClassificacao() {
        ClassificacaoDTO classificacaoDTO = new ClassificacaoDTO();
        final List<Time> times = timeServico.findAll();
        times.forEach(time -> {
            final List<Jogo> jogosMandante = jogoRepository.findByTime1AndEncerrado(time,true);
            final List<Jogo> jogosVisitante = jogoRepository.findByTime2AndEncerrado(time,true);
            AtomicReference<Integer> vitorias = new AtomicReference<>(0);
            AtomicReference<Integer> derrotas = new AtomicReference<>(0);
            AtomicReference<Integer> empates = new AtomicReference<>(0);
            AtomicReference<Integer> golsMarcardos = new AtomicReference<>(0);
            AtomicReference<Integer> golsSofridos = new AtomicReference<>(0);

            jogosMandante.forEach(jogo -> {
                if(jogo.getGolsTime1() > jogo.getGolsTime2()){
                    vitorias.getAndSet(vitorias.get() + 1);
                } else if (jogo.getGolsTime1()<jogo.getGolsTime2()) {
                    derrotas.getAndSet(derrotas.get() + 1);
                } else {
                    empates.getAndSet(empates.get() + 1);
                }
                golsMarcardos.set(golsMarcardos.get() + jogo.getGolsTime1());
                golsSofridos.set(golsMarcardos.get() + jogo.getGolsTime2());
            });

            jogosVisitante.forEach(jogo -> {
                if(jogo.getGolsTime2() > jogo.getGolsTime1()){
                    vitorias.getAndSet(vitorias.get() + 1);
                } else if (jogo.getGolsTime2()<jogo.getGolsTime1()) {
                    derrotas.getAndSet(derrotas.get() + 1);
                } else {
                    empates.getAndSet(empates.get() + 1);
                }
                golsMarcardos.set(golsMarcardos.get() + jogo.getGolsTime2());
                golsSofridos.set(golsMarcardos.get() + jogo.getGolsTime1());
            });

            ClassificacaoTimeDTO classificacaoTimeDTO = new ClassificacaoTimeDTO();

            classificacaoTimeDTO.setIdTime(time.getId());
            classificacaoTimeDTO.setTime(time.getNome());
            classificacaoTimeDTO.setDerrotas(derrotas.get());
            classificacaoTimeDTO.setVitorias(vitorias.get());
            classificacaoTimeDTO.setEmpates(empates.get());
            classificacaoTimeDTO.setPontos((vitorias.get() * 3) + empates.get());
            classificacaoTimeDTO.setGolsMarcados(golsMarcardos.get());
            classificacaoTimeDTO.setGolsSofridos(golsSofridos.get());
            classificacaoTimeDTO.setJogos(derrotas.get() + empates.get() + vitorias.get());
            classificacaoDTO.getTimes().add(classificacaoTimeDTO);

        });

        Collections.sort(classificacaoDTO.getTimes(), Collections.reverseOrder());
        int posicao = 0;
        for(ClassificacaoTimeDTO time : classificacaoDTO.getTimes()) {
            time.setPosicao(posicao++);
        }

        return  classificacaoDTO;
    }

}
