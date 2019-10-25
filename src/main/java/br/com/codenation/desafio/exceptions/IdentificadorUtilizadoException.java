package br.com.codenation.desafio.exceptions;

import br.com.codenation.desafio.utils.MensagensExceptions;

public class IdentificadorUtilizadoException extends RuntimeException{
    public IdentificadorUtilizadoException() {
        super(MensagensExceptions.MSG_IDENTIFICADOR_UTILIZADO);
    }
}
