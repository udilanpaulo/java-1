package br.com.codenation.desafio.exceptions;

import br.com.codenation.desafio.utils.MensagensExceptions;

public class TimeNaoEncontradoException extends RuntimeException{

    public TimeNaoEncontradoException () {
        super(MensagensExceptions.MSG_TIME_NAO_ENCONTRATO);
    }
}
