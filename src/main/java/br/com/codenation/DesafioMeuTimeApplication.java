package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.domain.Jogador;
import br.com.codenation.desafio.domain.Time;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.desafio.repository.JogadorRepository;
import br.com.codenation.desafio.repository.TimeRepository;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private final TimeRepository timeRepository = new TimeRepository();
	private final JogadorRepository jogadorRepository = new JogadorRepository();

	private void verificaJogadorExiste(Long idJogador) {
		if (!jogadorRepository.getJogadores().stream().filter(j -> j.getId().equals(Long.valueOf(idJogador))).findAny().isPresent()) {
			throw new JogadorNaoEncontradoException();
		}
	}

	private void verificaTimeExiste(Long idTime) {
		if (!timeRepository.getTimes().stream().filter(t -> t.getId() == idTime).findAny().isPresent()) {
			throw new TimeNaoEncontradoException();
		}
	}

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if (timeRepository.getTimes().stream().filter(t -> t.getId() == id).findAny().isPresent()) {
			throw new IdentificadorUtilizadoException();
		}
		Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		timeRepository.insere(time);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if (jogadorRepository.getJogadores().stream().filter(j -> j.getId() == id).findAny().isPresent()) {
			throw new IdentificadorUtilizadoException();
		}
		Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
		verificaTimeExiste(jogador.getIdTime());

		jogadorRepository.insere(jogador);
		timeRepository.getTime(jogador.getIdTime()).cadastrarJogador(jogador);
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		verificaJogadorExiste(idJogador);
		timeRepository.definirCapitao(idJogador);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		verificaTimeExiste(idTime);

		if (timeRepository.getTime(idTime).getIdCapitao().equals(null)) {
			throw new CapitaoNaoInformadoException();
		}

		return timeRepository.getTime(idTime).getIdCapitao();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		verificaJogadorExiste(idJogador);
		return jogadorRepository.getJogador(idJogador).getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		verificaTimeExiste(idTime);
		return timeRepository.getTime(idTime).getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		verificaTimeExiste(idTime);
		return timeRepository.getTime(idTime).getJogadores().stream()
				.map(Jogador::getId)
				.sorted()
				.collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		verificaTimeExiste(idTime);

		return timeRepository.getTime(idTime).getJogadores()
				.stream().sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed()
						.thenComparing(Jogador::getId))
				.findFirst().get().getId();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		verificaTimeExiste(idTime);

		return timeRepository.getTime(idTime).getJogadores()
				.stream().sorted(Comparator.comparing(Jogador::getDataNascimento)
						.thenComparing(Jogador::getId))
				.findFirst().get().getId();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return timeRepository.getTimes().stream().map(Time::getId).collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		verificaTimeExiste(idTime);
		return timeRepository.getTime(idTime).getJogadores()
				.stream().sorted(Comparator.comparing(Jogador::getSalario).reversed()
						.thenComparing(Jogador::getId))
				.findFirst().get().getId();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		verificaJogadorExiste(idJogador);
		return jogadorRepository.getJogador(idJogador).getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		if (!jogadorRepository.getJogadores().isEmpty()) {
			return jogadorRepository.getJogadores().stream()
					.sorted(Comparator.comparing(Jogador::getNivelHabilidade)
							.reversed()
							.thenComparing(Jogador::getId))
					.map(jogador -> jogador.getId())
					.collect(Collectors.toList())
					.subList(0, top);
		} else {
		    return new ArrayList<Long>();
        }
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		if (timeRepository.getTime(timeDaCasa).getCorUniformePrincipal().equals(timeRepository.getTime(timeDeFora).getCorUniformePrincipal())) {
			return timeRepository.getTime(timeDeFora).getCorUniformeSecundario();
		} else {
			return timeRepository.getTime(timeDeFora).getCorUniformePrincipal();
		}
	}

}
