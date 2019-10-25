package br.com.codenation.desafio.exceptions;

import br.com.codenation.desafio.utils.MensagensExceptions;

public class CapitaoNaoInformadoException extends RuntimeException {
    public CapitaoNaoInformadoException() {
        super(MensagensExceptions.MSG_CAPITAO_NAO_NFORMADO);
    }
}
