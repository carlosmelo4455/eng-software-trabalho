package org.biblioteca.exception;

public class UsuarioComEmprestimoEmAtrasoException extends RuntimeException {
    public UsuarioComEmprestimoEmAtrasoException() {
        super("Usuário com empréstimo em atraso");
    }
}
