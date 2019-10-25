package br.com.codenation.desafio.repository;

import br.com.codenation.desafio.domain.Jogador;

import java.util.ArrayList;
import java.util.List;

public class JogadorRepository {
    private final List<Jogador> jogadores = new ArrayList<>();

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void insere(Jogador jogador) {
        jogadores.add(jogador);
    }

    public Jogador getJogador(Long idJogador) {
        return jogadores.stream().filter(j -> j.getId().equals(idJogador)).distinct().findFirst().orElse(null);
    }
}

