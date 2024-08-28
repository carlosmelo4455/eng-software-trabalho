package org.biblioteca.exception;

public class EmprestimoNaoEncontradoException extends RuntimeException {
    public EmprestimoNaoEncontradoException() {
        super("Emprestimo não encontrado");
    }
}
