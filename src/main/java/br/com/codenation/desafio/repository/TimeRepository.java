package br.com.codenation.desafio.repository;

import br.com.codenation.desafio.domain.Time;

import java.util.ArrayList;
import java.util.List;

public class TimeRepository {
    private final List<Time> times = new ArrayList<>();

    public List<Time> getTimes() {
        return times;
    }

    public void insere(Time time) {
        times.add(time);
    }

    public Time getTime(Long idTime) {
        return times.stream().filter(t -> t.getId() == idTime).distinct().findFirst().get();
    }

    public void definirCapitao(Long idJogador) {
        times.stream().filter(time -> time.getJogadores().stream()
                .filter(j -> j.getId().equals(Long.valueOf(idJogador)))
                .count() > 0)
                .distinct().findFirst().orElse(null).setCapitao(idJogador);

    }
}

