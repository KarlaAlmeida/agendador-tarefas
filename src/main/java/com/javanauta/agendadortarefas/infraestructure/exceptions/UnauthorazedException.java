package com.javanauta.agendadortarefas.infraestructure.exceptions;

import javax.naming.AuthenticationException;

public class UnauthorazedException extends AuthenticationException {

    public UnauthorazedException(String mensagem){
        super(mensagem);
    }

    public UnauthorazedException(String mensagem, Throwable throwable){
        super(mensagem);
    }
}
