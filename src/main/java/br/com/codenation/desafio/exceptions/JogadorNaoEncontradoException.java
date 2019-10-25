package br.com.codenation.desafio.exceptions;

import br.com.codenation.desafio.utils.MensagensExceptions;

public class JogadorNaoEncontradoException extends RuntimeException{
    public JogadorNaoEncontradoException () {
        super(MensagensExceptions.MSG_JOGADOR_NAO_ENCONTRATO);
    }
}
