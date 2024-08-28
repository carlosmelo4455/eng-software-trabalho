package org.biblioteca.exception;

public class EmprestimoAtivoException extends RuntimeException {
    public EmprestimoAtivoException() {
        super("Usuário já possui um empréstimo do mesmo livro ativo");
    }
}
