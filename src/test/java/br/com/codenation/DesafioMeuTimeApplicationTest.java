package br.com.codenation;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.desafio.utils.MensagensExceptions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DesafioMeuTimeApplicationTest {
    private DesafioMeuTimeApplication desafioMeuTimeApplication;

    @Before
    public void setUp() {
        desafioMeuTimeApplication = new DesafioMeuTimeApplication();
    }

    @Rule
    public final ExpectedException excecaoEsperada = ExpectedException.none();

    @Test(expected = IdentificadorUtilizadoException.class)
    public void incluirTimeDuplicado() {
        desafioMeuTimeApplication.incluirTime(1L, "Time 01", LocalDate.now(), "Preto", "Branco");
        desafioMeuTimeApplication.incluirTime(1L, "Time 01", LocalDate.now(), "Preto", "Branco");

        excecaoEsperada.expectMessage(MensagensExceptions.MSG_IDENTIFICADOR_UTILIZADO);
    }

    @Test
    public void incluirTime() {
        desafioMeuTimeApplication.incluirTime(1L, "Time 01", LocalDate.now(), "Preto", "Branco");

        assertEquals("Time 01", desafioMeuTimeApplication.buscarNomeTime(1L));
    }

    @Test(expected = IdentificadorUtilizadoException.class)
    public void incluirJogadorDuplicado() {
        desafioMeuTimeApplication.incluirTime(1L, "Time 01", LocalDate.now(), "Preto", "Branco");

        desafioMeuTimeApplication.incluirJogador(1L,1L, "Paulo", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));
        desafioMeuTimeApplication.incluirJogador(1L,1L, "Paulo", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));

        excecaoEsperada.expectMessage(MensagensExceptions.MSG_IDENTIFICADOR_UTILIZADO);
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void incluirJogadorTimeNaoEncontrado() {
        desafioMeuTimeApplication.incluirJogador(1L,88L, "Paulo", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));

        excecaoEsperada.expectMessage(MensagensExceptions.MSG_TIME_NAO_ENCONTRATO);
    }

    @Test
    public void incluirJogador() {
        desafioMeuTimeApplication.incluirTime(1L, "Time 01", LocalDate.now(), "Preto", "Branco");
        desafioMeuTimeApplication.incluirJogador(1L,1L, "Paulo", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));

        assertEquals("Paulo",desafioMeuTimeApplication.buscarNomeJogador(1L));
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void definirCapitaoNaoEncontrado() {
        desafioMeuTimeApplication.definirCapitao(88L);

        excecaoEsperada.expectMessage(MensagensExceptions.MSG_JOGADOR_NAO_ENCONTRATO);
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarCapitaoDoTimeNaoEncontrato() {
        desafioMeuTimeApplication.buscarCapitaoDoTime(88L);

        excecaoEsperada.expectMessage(MensagensExceptions.MSG_TIME_NAO_ENCONTRATO);
    }

    @Test(expected = CapitaoNaoInformadoException.class)
    public void buscarCapitaoDoTimeSemCapitao() {
        desafioMeuTimeApplication.incluirTime(2L, "Time 02", LocalDate.now(), "Azul", "Vermelho");
        desafioMeuTimeApplication.incluirJogador(1L,2L, "Paulo", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));
        desafioMeuTimeApplication.incluirJogador(2L,2L, "Paulo2", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));

        desafioMeuTimeApplication.buscarCapitaoDoTime(2L);

        excecaoEsperada.expectMessage(MensagensExceptions.MSG_CAPITAO_NAO_NFORMADO);
    }

    @Test
    public void buscarCapitaoDoTime() {
        desafioMeuTimeApplication.incluirTime(1L, "Time 01", LocalDate.now(), "Preto", "Branco");
        desafioMeuTimeApplication.incluirJogador(1L,1L, "Paulo", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));
        desafioMeuTimeApplication.definirCapitao(1L);
        Long capitao = desafioMeuTimeApplication.buscarCapitaoDoTime(1L);
        assertEquals(Long.valueOf(1), capitao);
    }

    @Test
    public void definirCapitao() {
        desafioMeuTimeApplication.incluirTime(1L, "Time 01", LocalDate.now(), "Preto", "Branco");
        desafioMeuTimeApplication.incluirJogador(1L,1L, "Paulo", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));
        desafioMeuTimeApplication.definirCapitao(1L);

        assertEquals(Long.valueOf(1), desafioMeuTimeApplication.buscarCapitaoDoTime(1L));
    }


    @Test
    public void buscarNomeJogador() {
        desafioMeuTimeApplication.incluirTime(2L, "Time 02", LocalDate.now(), "Azul", "Vermelho");
        desafioMeuTimeApplication.incluirJogador(1L,2L, "Paulo", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));
        String nome = desafioMeuTimeApplication.buscarNomeJogador(1L);
        assertEquals("Paulo", nome);
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void buscarNomeJogadorNaoEncontrado() {
        desafioMeuTimeApplication.incluirTime(2L, "Time 02", LocalDate.now(), "Azul", "Vermelho");
        desafioMeuTimeApplication.incluirJogador(1L,2L, "Paulo", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));

        desafioMeuTimeApplication.buscarNomeJogador(2L);

        excecaoEsperada.expectMessage(MensagensExceptions.MSG_JOGADOR_NAO_ENCONTRATO);
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarNomeTimeNaoEncontrado() {
        desafioMeuTimeApplication.incluirTime(2L, "Time 02", LocalDate.now(), "Azul", "Vermelho");
        desafioMeuTimeApplication.buscarNomeTime(1L);

        excecaoEsperada.expectMessage(MensagensExceptions.MSG_TIME_NAO_ENCONTRATO);
    }

    @Test
    public void buscarNomeTimeEncontrado() {
        desafioMeuTimeApplication.incluirTime(2L, "Time 02", LocalDate.now(), "Azul", "Vermelho");
        String nome = desafioMeuTimeApplication.buscarNomeTime(2L);
        assertEquals("Time 02",nome);
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarJogadoresDoTimeNaoEncontrado() {
        desafioMeuTimeApplication.incluirTime(1L, "Time 01", LocalDate.now(), "Preto", "Branco");
        desafioMeuTimeApplication.incluirJogador(1L,1L, "Paulo", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));
        desafioMeuTimeApplication.incluirJogador(2L,1L, "Paulo2", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));

        desafioMeuTimeApplication.buscarJogadoresDoTime(2L);

        excecaoEsperada.expectMessage(MensagensExceptions.MSG_TIME_NAO_ENCONTRATO);
    }

    @Test
    public void buscarJogadoresDoTimeEncontrado() {
        desafioMeuTimeApplication.incluirTime(1L, "Time 01", LocalDate.now(), "Preto", "Branco");
        desafioMeuTimeApplication.incluirJogador(1L,1L, "Paulo", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));
        desafioMeuTimeApplication.incluirJogador(2L,1L, "Paulo2", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));
        List<Long> lista = desafioMeuTimeApplication.buscarJogadoresDoTime(1L);
        assertEquals(2, lista.size());
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarMelhorJogadorDoTimeNaoEncontrado() {
        desafioMeuTimeApplication.incluirTime(1L, "Time 01", LocalDate.now(), "Preto", "Branco");
        desafioMeuTimeApplication.incluirJogador(1L,1L, "Paulo1", LocalDate.of(1988, Month.OCTOBER, 24), 10, BigDecimal.valueOf(2500.00));
        desafioMeuTimeApplication.incluirJogador(2L,1L, "Paulo2", LocalDate.of(1988, Month.OCTOBER, 23), 9, BigDecimal.valueOf(3500.00));
        desafioMeuTimeApplication.incluirJogador(3L,1L, "Paulo3", LocalDate.of(1988, Month.OCTOBER, 13), 10, BigDecimal.valueOf(4500.00));
        desafioMeuTimeApplication.incluirJogador(5L,1L, "Paulo5", LocalDate.of(1988, Month.OCTOBER, 15), 11, BigDecimal.valueOf(4500.00));
        desafioMeuTimeApplication.incluirJogador(4L,1L, "Paulo4", LocalDate.of(1988, Month.OCTOBER, 13), 11, BigDecimal.valueOf(4000.00));

        desafioMeuTimeApplication.buscarMelhorJogadorDoTime(2L);

        excecaoEsperada.expectMessage(MensagensExceptions.MSG_TIME_NAO_ENCONTRATO);
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarMelhorJogadorDoTimeEncontrado() {
        desafioMeuTimeApplication.incluirTime(1L, "Time 01", LocalDate.now(), "Preto", "Branco");
        desafioMeuTimeApplication.incluirJogador(1L,1L, "Paulo1", LocalDate.of(1988, Month.OCTOBER, 24), 10, BigDecimal.valueOf(2500.00));
        desafioMeuTimeApplication.incluirJogador(2L,1L, "Paulo2", LocalDate.of(1988, Month.OCTOBER, 23), 9, BigDecimal.valueOf(3500.00));
        desafioMeuTimeApplication.incluirJogador(3L,1L, "Paulo3", LocalDate.of(1988, Month.OCTOBER, 13), 10, BigDecimal.valueOf(4500.00));
        desafioMeuTimeApplication.incluirJogador(5L,1L, "Paulo5", LocalDate.of(1988, Month.OCTOBER, 15), 11, BigDecimal.valueOf(4500.00));
        desafioMeuTimeApplication.incluirJogador(4L,1L, "Paulo4", LocalDate.of(1988, Month.OCTOBER, 13), 11, BigDecimal.valueOf(4000.00));
        Long melhor = desafioMeuTimeApplication.buscarMelhorJogadorDoTime(2L);
        assertEquals(Long.valueOf(4),melhor);

        excecaoEsperada.expectMessage(MensagensExceptions.MSG_TIME_NAO_ENCONTRATO);
    }

    @Test
    public void buscarJogadorMaisVelhoEncontrado() {
        desafioMeuTimeApplication.incluirTime(1L, "Time 01", LocalDate.now(), "Preto", "Branco");
        desafioMeuTimeApplication.incluirJogador(1L,1L, "Paulo1", LocalDate.of(1988, Month.OCTOBER, 24), 10, BigDecimal.valueOf(2500.00));
        desafioMeuTimeApplication.incluirJogador(2L,1L, "Paulo2", LocalDate.of(1988, Month.OCTOBER, 23), 9, BigDecimal.valueOf(3500.00));
        desafioMeuTimeApplication.incluirJogador(3L,1L, "Paulo3", LocalDate.of(1988, Month.OCTOBER, 13), 10, BigDecimal.valueOf(4500.00));
        desafioMeuTimeApplication.incluirJogador(5L,1L, "Paulo5", LocalDate.of(1988, Month.OCTOBER, 15), 11, BigDecimal.valueOf(4500.00));
        desafioMeuTimeApplication.incluirJogador(4L,1L, "Paulo4", LocalDate.of(1988, Month.OCTOBER, 13), 11, BigDecimal.valueOf(4000.00));
        Long velho = desafioMeuTimeApplication.buscarJogadorMaisVelho(1L);
        assertEquals(Long.valueOf(3),velho);
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarJogadorMaisVelhoNaoEncontrado() {
        desafioMeuTimeApplication.incluirTime(1L, "Time 01", LocalDate.now(), "Preto", "Branco");
        desafioMeuTimeApplication.incluirJogador(1L,1L, "Paulo1", LocalDate.of(1988, Month.OCTOBER, 24), 10, BigDecimal.valueOf(2500.00));
        desafioMeuTimeApplication.incluirJogador(2L,1L, "Paulo2", LocalDate.of(1988, Month.OCTOBER, 23), 9, BigDecimal.valueOf(3500.00));
        desafioMeuTimeApplication.incluirJogador(3L,1L, "Paulo3", LocalDate.of(1988, Month.OCTOBER, 13), 10, BigDecimal.valueOf(4500.00));
        desafioMeuTimeApplication.incluirJogador(5L,1L, "Paulo5", LocalDate.of(1988, Month.OCTOBER, 15), 11, BigDecimal.valueOf(4500.00));
        desafioMeuTimeApplication.incluirJogador(4L,1L, "Paulo4", LocalDate.of(1988, Month.OCTOBER, 13), 11, BigDecimal.valueOf(4000.00));
        desafioMeuTimeApplication.buscarJogadorMaisVelho(2L);

        excecaoEsperada.expectMessage(MensagensExceptions.MSG_TIME_NAO_ENCONTRATO);
    }

    @Test
    public void buscarTimesVazia() {
        List<Long> lista = desafioMeuTimeApplication.buscarTimes();
        assertEquals(0,lista.size());
    }

    @Test
    public void buscarTimesCheia() {
        desafioMeuTimeApplication.incluirTime(1L,"Time1", LocalDate.now(), "Azul", "Azul");
        desafioMeuTimeApplication.incluirTime(2L,"Time2", LocalDate.now(), "Branco", "Azul");
        desafioMeuTimeApplication.incluirTime(3L,"Time3", LocalDate.now(), "Preto", "Azul");

        List<Long> lista = desafioMeuTimeApplication.buscarTimes();
        assertEquals(3,lista.size());
    }

    @Test
    public void buscarJogadorMaiorSalarioEncontrado() {
        desafioMeuTimeApplication.incluirTime(1L, "Time 01", LocalDate.now(), "Preto", "Branco");
        desafioMeuTimeApplication.incluirJogador(1L,1L, "Paulo1", LocalDate.of(1988, Month.OCTOBER, 24), 10, BigDecimal.valueOf(2500.00));
        desafioMeuTimeApplication.incluirJogador(2L,1L, "Paulo2", LocalDate.of(1988, Month.OCTOBER, 23), 9, BigDecimal.valueOf(3500.00));
        desafioMeuTimeApplication.incluirJogador(3L,1L, "Paulo3", LocalDate.of(1988, Month.OCTOBER, 13), 10, BigDecimal.valueOf(4500.00));
        desafioMeuTimeApplication.incluirJogador(5L,1L, "Paulo5", LocalDate.of(1988, Month.OCTOBER, 15), 11, BigDecimal.valueOf(4500.00));
        desafioMeuTimeApplication.incluirJogador(4L,1L, "Paulo4", LocalDate.of(1988, Month.OCTOBER, 13), 11, BigDecimal.valueOf(4000.00));
        Long salario = desafioMeuTimeApplication.buscarJogadorMaiorSalario(1L);
        assertEquals(Long.valueOf(3),salario);
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void buscarJogadorMaiorSalarioNaoEncontrado() {
        desafioMeuTimeApplication.incluirTime(1L, "Time 01", LocalDate.now(), "Preto", "Branco");
        desafioMeuTimeApplication.incluirJogador(1L,1L, "Paulo1", LocalDate.of(1988, Month.OCTOBER, 24), 10, BigDecimal.valueOf(2500.00));
        desafioMeuTimeApplication.incluirJogador(2L,1L, "Paulo2", LocalDate.of(1988, Month.OCTOBER, 23), 9, BigDecimal.valueOf(3500.00));
        desafioMeuTimeApplication.incluirJogador(3L,1L, "Paulo3", LocalDate.of(1988, Month.OCTOBER, 13), 10, BigDecimal.valueOf(4500.00));
        desafioMeuTimeApplication.incluirJogador(5L,1L, "Paulo5", LocalDate.of(1988, Month.OCTOBER, 15), 11, BigDecimal.valueOf(4500.00));
        desafioMeuTimeApplication.incluirJogador(4L,1L, "Paulo4", LocalDate.of(1988, Month.OCTOBER, 13), 11, BigDecimal.valueOf(4000.00));
        desafioMeuTimeApplication.buscarJogadorMaiorSalario(2L);

        excecaoEsperada.expectMessage(MensagensExceptions.MSG_TIME_NAO_ENCONTRATO);
    }

    @Test
    public void buscarSalarioDoJogadorEncontrado() {
        desafioMeuTimeApplication.incluirTime(2L, "Time 02", LocalDate.now(), "Azul", "Vermelho");
        desafioMeuTimeApplication.incluirJogador(1L,2L, "Paulo", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));
        BigDecimal valor = desafioMeuTimeApplication.buscarSalarioDoJogador(1L);
        assertEquals(BigDecimal.valueOf(2500.0),valor);
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void buscarSalarioDoJogadorNaoEncontrado() {
        desafioMeuTimeApplication.incluirTime(2L, "Time 02", LocalDate.now(), "Azul", "Vermelho");
        desafioMeuTimeApplication.incluirJogador(1L,2L, "Paulo", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));
        desafioMeuTimeApplication.buscarSalarioDoJogador(2L);

        excecaoEsperada.expectMessage(MensagensExceptions.MSG_JOGADOR_NAO_ENCONTRATO);
    }

    @Test
    public void buscarTopJogadoresVazio() {
        List<Long> jogadores = desafioMeuTimeApplication.buscarTopJogadores(3);
        assertEquals(0, jogadores.size());
    }

    @Test
    public void buscarTopJogadoresCheio() {
        desafioMeuTimeApplication.incluirTime(1L, "Time1", LocalDate.now(),"Azul", "Preto");
        desafioMeuTimeApplication.incluirJogador(1L,1L, "Paulo1", LocalDate.of(1988, Month.OCTOBER, 23), 9, BigDecimal.valueOf(2500.00));
        desafioMeuTimeApplication.incluirJogador(2L,1L, "Paulo2", LocalDate.of(1988, Month.OCTOBER, 23), 11, BigDecimal.valueOf(2500.00));
        desafioMeuTimeApplication.incluirJogador(5L,1L, "Paulo5", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));
        desafioMeuTimeApplication.incluirJogador(4L,1L, "Paulo4", LocalDate.of(1988, Month.OCTOBER, 23), 11, BigDecimal.valueOf(2500.00));
        desafioMeuTimeApplication.incluirJogador(3L,1L, "Paulo3", LocalDate.of(1988, Month.OCTOBER, 23), 10, BigDecimal.valueOf(2500.00));

        List<Long> jogadores = desafioMeuTimeApplication.buscarTopJogadores(3);
        List<Long> certos = new ArrayList<>();
        certos.add(2L);
        certos.add(4L);
        certos.add(3L);

        assertEquals(certos, jogadores);
    }

    @Test
    public void buscarCorCamisaTimeDeFora() {
        desafioMeuTimeApplication.incluirTime(1L, "Time 01", LocalDate.now(), "Preto", "Branco");
        desafioMeuTimeApplication.incluirTime(2L, "Time 02", LocalDate.of(2007, Month.JANUARY, 1), "Preto", "Azul");

        Integer tam = desafioMeuTimeApplication.buscarTimes().size();

        String corTime = desafioMeuTimeApplication.buscarCorCamisaTimeDeFora(1L, 2L);
        assertEquals("Azul", corTime);
    }
}