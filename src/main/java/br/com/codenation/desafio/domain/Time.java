package br.com.codenation.desafio.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Time {
    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;

    private final List<Jogador> jogadores = new ArrayList<>();

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    private Long idCapitao;

    public Long getIdCapitao() {
        return idCapitao;
    }

    public void setCapitao(Long idCapitao) {
        this.idCapitao = idCapitao;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    private void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    private void setCorUniformePrincipal(String corUniformePrincipal) {
        this.corUniformePrincipal = corUniformePrincipal;
    }

    public String getCorUniformeSecundario() {
        return corUniformeSecundario;
    }

    private void setCorUniformeSecundario(String corUniformeSecundario) {
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public void cadastrarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        this.setId(id);
        this.setNome(nome);
        this.setDataCriacao(dataCriacao);
        this.setCorUniformePrincipal(corUniformePrincipal);
        this.setCorUniformeSecundario(corUniformeSecundario);
    }

}
